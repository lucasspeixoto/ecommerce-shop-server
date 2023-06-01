package com.ibm.shop.repositories;

import com.ibm.shop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserEmailOrderByDateCreatedDesc(@Param("email") String email);
}
