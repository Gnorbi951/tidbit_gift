package com.codecool.product.controller;

import com.codecool.product.entity.Product;
import com.codecool.product.model.ResponsePackage;
import com.codecool.product.service.ProductOrganiser;
import com.codecool.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/productservice")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductOrganiser productOrganiser;

    // TODO: get wrapper around the list?
    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productRepository.getById(id);
    }

    // TODO: get wrapper around the list?
    @GetMapping("/product/user/{id}")
    public List<Product> getAllProductsOfUser(@PathVariable("id") Long id) {
        return productRepository.getAllByUserId(id);
    }

    // NB: authentication handled by UserService
    // TODO: do we need a product wrapper class?
    @PostMapping("/product")
    public ResponsePackage addNewProduct(@RequestBody Product product) {
        return productOrganiser.addNewProduct(product);
    }

    // TODO: check what will we get from UserService to check userId!
    @DeleteMapping("/product/{id}/{userId}")
    public ResponsePackage deleteProduct(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        return productOrganiser.deleteProductOfUser(id, userId);
    }
}
