package com.ibm.shop.repositories;

import com.ibm.shop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from product p
                    WHERE p.category_id in (:categories)
                    AND p.rating >= :rating
                    AND p.unit_price BETWEEN :minPrice AND :maxPrice
            """)
    List<Product> findByFilters(Integer minPrice, Integer maxPrice, Integer rating, ArrayList<String> categories);

    @Query(nativeQuery = true, value = """
                    SELECT * from product p
                    WHERE p.on_sale = 1
            """)
    List<Product> findOnSale();

    @Query(nativeQuery = true, value = """
                    SELECT * from product p
                    WHERE p.category_id = :id
            """)
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    @Query(nativeQuery = true, value = """
                    SELECT * from product p
                    WHERE p.name LIKE %:name
            """)
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

}
