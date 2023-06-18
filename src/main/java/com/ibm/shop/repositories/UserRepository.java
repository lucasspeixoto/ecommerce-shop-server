package com.ibm.shop.repositories;

import com.ibm.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    //@Query("SELECT u from User u where u.userName =:userName")
    @Query(nativeQuery = true, value = """
                    SELECT * from users u
                    WHERE u.user_name = :userName
            """)
    User findByUserName(@Param("userName") String userName);
}
