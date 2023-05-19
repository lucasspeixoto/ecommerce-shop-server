package com.ibm.shop.repositories;

import com.ibm.shop.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT s from State s WHERE s.country.id = :code")
    List<State> findByCountryCode(@Param("code") Long code);
}
