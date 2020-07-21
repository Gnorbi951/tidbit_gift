package com.codecool.apigateway.repository;

import com.codecool.apigateway.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity getUserById(Long id);

    @Query("SELECT u.password FROM UserEntity u WHERE u.id =:id")
    String getPasswordById(@Param("id")Long id);

    UserEntity getUserEntityByName(String name);

    Optional<UserEntity> findByName(String name);
}
