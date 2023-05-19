package com.ibm.shop.repositories;

import com.ibm.shop.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * collectionResourceRel: Name of JSON entry
 * path:
 */
@RepositoryRestResource(collectionResourceRel = "productCategory", path= "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
