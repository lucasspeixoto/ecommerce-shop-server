package com.ibm.shop.repositories;

import com.ibm.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u where u.userName =:userName")
    User findByUsername(@Param("userName") String userName);
}
