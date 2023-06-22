package com.ibm.shop.services;

import com.ibm.shop.data.response.ProductCategoryResponse;
import com.ibm.shop.data.vo.ProductCategoryVO;
import com.ibm.shop.entities.Product;
import com.ibm.shop.entities.ProductCategory;
import com.ibm.shop.exceptions.ResourceNotFoundException;
import com.ibm.shop.mapper.IbmShopMapper;
import com.ibm.shop.repositories.ProductCategoryRepository;
import com.ibm.shop.utils.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductCategoryService {

    private final Logger logger = Logger.getLogger(ProductCategoryService.class.getName());

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private PagedResourcesAssembler<ProductCategoryVO> assembler;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON}
    )
    public ProductCategoryResponse findAll(Pageable pageable) throws Exception {

        logger.info("Finding all categories!");

        Page<ProductCategory> pageableCategories = repository.findAll(pageable);

        List<ProductCategory> pageableProductCategoryContent = pageableCategories.getContent();

        List<ProductCategoryVO> content = convertEntitiesToDTOs(pageableProductCategoryContent);

        ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();

        productCategoryResponse.setContent(content);
        productCategoryResponse.setPageNo(pageableCategories.getNumber());
        productCategoryResponse.setPageSize(pageableCategories.getSize());
        productCategoryResponse.setTotalElements(pageableCategories.getTotalElements());
        productCategoryResponse.setTotalPages(pageableCategories.getTotalPages());
        productCategoryResponse.setLast(pageableCategories.isLast());

        return productCategoryResponse;
    }

    public ProductCategoryVO findById(Long id) {
        logger.info("Finding a category by Id");

        ProductCategory entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        return convertEntityToDTO(entity);

    }

    //! Mapper methods ---------------------------------------------------------------------------
    private ProductCategoryVO convertEntityToDTO(ProductCategory entity) {
        return IbmShopMapper.parseObject(entity, ProductCategoryVO.class, modelMapper);
    }

    private ProductCategory convertDTOToEntity(ProductCategoryVO dto) {
        return IbmShopMapper.parseObject(dto, ProductCategory.class, modelMapper);
    }

    private List<ProductCategoryVO> convertEntitiesToDTOs(List<ProductCategory> entities) {
        return IbmShopMapper.parseListObjects(entities, ProductCategoryVO.class, modelMapper);
    }

    private List<ProductCategory> convertDTOsToEntities(List<ProductCategoryVO> dto) {
        return IbmShopMapper.parseListObjects(dto, ProductCategory.class, modelMapper);
    }
    //! --------------------------------------------------------------------------- Mapper methods


}
