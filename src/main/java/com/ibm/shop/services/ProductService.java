package com.ibm.shop.services;

import com.ibm.shop.data.response.ProductResponse;
import com.ibm.shop.data.vo.ProductVO;
import com.ibm.shop.entities.Product;
import com.ibm.shop.exceptions.ResourceNotFoundException;
import com.ibm.shop.mapper.IbmShopMapper;
import com.ibm.shop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductService {

    private final Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    private ProductRepository repository;

    @Autowired
    private PagedResourcesAssembler<ProductVO> assembler;

    @Autowired
    private PagedResourcesAssembler<Product> assembler_;

    @Autowired
    private ModelMapper modelMapper;

    public ProductResponse findAll(Pageable pageable) throws Exception {

        logger.info("Finding all products!");

        Page<Product> pageableProducts = repository.findAll(pageable);

        List<Product> pageableProductContent = pageableProducts.getContent();

        List<ProductVO> content = convertEntitiesToDTOs(pageableProductContent);

        ProductResponse productResponse = new ProductResponse();

        productResponse.setContent(content);
        productResponse.setPageNo(pageableProducts.getNumber());
        productResponse.setPageSize(pageableProducts.getSize());
        productResponse.setTotalElements(pageableProducts.getTotalElements());
        productResponse.setTotalPages(pageableProducts.getTotalPages());
        productResponse.setLast(pageableProducts.isLast());

        return productResponse;

    }

    public ProductVO findById(Long id) {
        logger.info("Finding a product by Id");

        Product entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product", "id", id)
                );

        return this.convertEntityToDTO(entity);

    }

    public ProductVO update(ProductVO product) {

        logger.info("Updating a product");

        var entity = this.repository.findById(product.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", product.getId())
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

        Product newProduct = this.repository.save(entity);


        return this.convertEntityToDTO(newProduct);

    }

    public ProductVO create(ProductVO productVO) {

        logger.info("Creating a product");

        Product product = this.convertDTOToEntity(productVO);

        Product newProduct = this.repository.save(product);

        return this.convertEntityToDTO(newProduct);

        /*
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

         */
    }

    public ProductResponse findByCategoryId(Long id, Pageable pageable) throws Exception {
        logger.info("Finding products by specific category id");


        Page<Product> pageableProducts = repository.findByCategoryId(id, pageable);

        List<Product> pageableProductContent = pageableProducts.getContent();

        List<ProductVO> content = convertEntitiesToDTOs(pageableProductContent);

        ProductResponse productResponse = new ProductResponse();

        productResponse.setContent(content);
        productResponse.setPageNo(pageableProducts.getNumber());
        productResponse.setPageSize(pageableProducts.getSize());
        productResponse.setTotalElements(pageableProducts.getTotalElements());
        productResponse.setTotalPages(pageableProducts.getTotalPages());
        productResponse.setLast(pageableProducts.isLast());

        return productResponse;
    }

    public ProductResponse findByNameContaining(String name, Pageable pageable) throws Exception {
        logger.info("Finding products by specific category id");

        Page<Product> pageableProducts = repository.findByNameContaining(name, pageable);

        List<Product> pageableProductContent = pageableProducts.getContent();

        List<ProductVO> content = convertEntitiesToDTOs(pageableProductContent);

        ProductResponse productResponse = new ProductResponse();

        productResponse.setContent(content);
        productResponse.setPageNo(pageableProducts.getNumber());
        productResponse.setPageSize(pageableProducts.getSize());
        productResponse.setTotalElements(pageableProducts.getTotalElements());
        productResponse.setTotalPages(pageableProducts.getTotalPages());
        productResponse.setLast(pageableProducts.isLast());

        return productResponse;
    }

    public ProductVO delete(Long id) {
        Product product = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", id)
        );

        this.repository.delete(product);

        return this.convertEntityToDTO(product);
    }


    //! Mapper methods ---------------------------------------------------------------------------
    private ProductVO convertEntityToDTO(Product entity) {
        return IbmShopMapper.parseObject(entity, ProductVO.class, modelMapper);
    }

    private Product convertDTOToEntity(ProductVO postDTO) {
        return IbmShopMapper.parseObject(postDTO, Product.class, modelMapper);
    }

    private List<ProductVO> convertEntitiesToDTOs(List<Product> entities) {
        return IbmShopMapper.parseListObjects(entities, ProductVO.class, modelMapper);
    }

    private List<Product> convertDTOsToEntities(List<ProductVO> products) {
        return IbmShopMapper.parseListObjects(products, Product.class, modelMapper);
    }
    //! --------------------------------------------------------------------------- Mapper methods
}
