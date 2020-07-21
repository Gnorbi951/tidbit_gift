package com.codecool.apigateway.controller;

import com.codecool.apigateway.model.Product;
import com.codecool.apigateway.service.ProductServiceCaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productservice")
@Slf4j
public class ProductServiceController {

    @Autowired
    private ProductServiceCaller productServiceCaller;

    @GetMapping("/product")
    private List<Product> getAllProducts() {
        return productServiceCaller.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productServiceCaller.getById(id);
    }

    // TODO: get wrapper around the list?
    @GetMapping("/product/user/{id}")
    public List<Product> getAllProductsOfUser(@PathVariable("id") Long id) {
        return productServiceCaller.getAllByUserId(id);
    }
}
