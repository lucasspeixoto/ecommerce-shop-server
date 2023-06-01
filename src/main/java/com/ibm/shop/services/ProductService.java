package com.ibm.shop.services;

import com.ibm.shop.controllers.ProductController;
import com.ibm.shop.data.vo.ProductVO;
import com.ibm.shop.entities.Product;
import com.ibm.shop.exceptions.RequiredObjectIsNullException;
import com.ibm.shop.exceptions.ResourceNotFoundException;
import com.ibm.shop.mapper.ProductMapper;
import com.ibm.shop.repositories.ProductRepository;
import com.ibm.shop.utils.MediaType;
import org.modelmapper.ModelMapper;
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
public class ProductService {

    private final Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    private ProductRepository repository;

    @Autowired
    private PagedResourcesAssembler<ProductVO> assembler;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON}
    )
    public PagedModel<EntityModel<ProductVO>> findAll(Pageable pageable) throws Exception {

        logger.info("Finding all products!");

        var products = repository.findAll(pageable);

        var mapper = new ModelMapper();

        mapper.createTypeMap(Product.class, ProductVO.class)
                .addMapping(Product::getId, ProductVO::setKey);

        var productsVos = products.map(
                product -> ProductMapper.parseObject(product, ProductVO.class, mapper)
        );

        productsVos.map(product -> product
                .add(
                        linkTo(methodOn(ProductController.class)
                                .findById(product.getKey())
                        ).withSelfRel()
                )
        );

        Link link = linkTo(methodOn(ProductController.class)
                .findAll(pageable)
        ).withSelfRel();

        return assembler.toModel(productsVos, link);

    }

    public ProductVO findById(Long id) {
        logger.info("Finding a product by Id");

        var entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No records found for this id")
                );

        var mapper = new ModelMapper();

        mapper
                .createTypeMap(Product.class, ProductVO.class)
                .addMapping(Product::getId, ProductVO::setKey);

        ProductVO productViewObject = ProductMapper.parseObject(entity, ProductVO.class, mapper);

        productViewObject
                .add(linkTo(methodOn(ProductController.class)
                        .findById(id)
                ).withSelfRel());

        return productViewObject;

    }

    public ProductVO update(ProductVO product) {

        if (product == null) throw new RequiredObjectIsNullException();

        logger.info("Updating a product");

        var entity = this.repository.findById(product.getKey()).orElseThrow(
                () -> new ResourceNotFoundException("No record founds for this ID!")
        );

        entity.setSku(product.getSku());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setUnitPrice(product.getUnitPrice());
        entity.setImageUrl(product.getImageUrl());
        entity.setActive(product.isActive());
        entity.setUnitsInStock(product.getUnitsInStock());
        entity.setNewProduct(product.isNewProduct());
        entity.setRating(product.getRating());

        var mapper = new ModelMapper();

        mapper.createTypeMap(Product.class, ProductVO.class)
                .addMapping(Product::getId, ProductVO::setKey);

        var productVo = ProductMapper.parseObject(
                this.repository.save(entity),
                ProductVO.class, mapper
        );

        productVo
                .add(linkTo(methodOn(ProductController.class)
                        .findById(productVo.getKey())
                ).withSelfRel());

        return productVo;

    }

    public ProductVO create(ProductVO product) {
        if (product == null) throw new RequiredObjectIsNullException();

        logger.info("Creating a product");

        var mapper = new ModelMapper();

        mapper.createTypeMap(Product.class, ProductVO.class)
                .addMapping(Product::getId, ProductVO::setKey);


        var entity = ProductMapper.parseObject(product, Product.class, mapper);

        var productVO = ProductMapper.parseObject(this.repository.save(entity), ProductVO.class, mapper);

        productVO
                .add(linkTo(methodOn(ProductController.class)
                        .findById(productVO.getKey())
                ).withSelfRel());

        return productVO;
    }
}
