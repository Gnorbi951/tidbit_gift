package com.codecool.product.repository;

import com.codecool.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Product getById(Long id);

    List<Product> getAllByUserId(Long userId);

    Product getByIdAndUserId(Long id, Long userId);

    boolean deleteByIdAndUserId(Long id, Long userId);
}
