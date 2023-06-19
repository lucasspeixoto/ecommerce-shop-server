package com.ibm.shop.services;

import com.ibm.shop.controllers.ProductCategoryController;
import com.ibm.shop.data.vo.ProductCategoryVO;
import com.ibm.shop.exceptions.ResourceNotFoundException;
import com.ibm.shop.mapper.ProductCategoryMapper;
import com.ibm.shop.repositories.ProductCategoryRepository;
import com.ibm.shop.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductCategoryService {

    private final Logger logger = Logger.getLogger(ProductCategoryService.class.getName());

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private PagedResourcesAssembler<ProductCategoryVO> assembler;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON}
    )
    public PagedModel<EntityModel<ProductCategoryVO>> findAll(Pageable pageable) throws Exception {

        logger.info("Finding all categories!");

        var products = repository.findAll(pageable);

        var productsVos = products.map(
                product -> ProductCategoryMapper.parseObject(product, ProductCategoryVO.class)
        );

        productsVos.map(product -> product
                .add(
                        linkTo(methodOn(ProductCategoryController.class)
                                .findById(product.getKey())
                        ).withSelfRel()
                )
        );

        Link link = linkTo(methodOn(ProductCategoryController.class)
                .findAll(pageable)
        ).withSelfRel();

        return assembler.toModel(productsVos, link);
    }

    public ProductCategoryVO findById(Long id) {
        logger.info("Finding a category by Id");

        var entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        ProductCategoryVO productViewObject = ProductCategoryMapper.parseObject(entity, ProductCategoryVO.class);

        productViewObject
                .add(linkTo(methodOn(ProductCategoryController.class)
                        .findById(id)
                ).withSelfRel());

        return productViewObject;

    }
}
