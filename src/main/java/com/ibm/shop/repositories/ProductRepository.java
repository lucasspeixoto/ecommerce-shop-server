package com.ibm.shop.repositories;

import com.ibm.shop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

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
