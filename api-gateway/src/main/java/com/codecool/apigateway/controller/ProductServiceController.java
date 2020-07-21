package com.codecool.apigateway.controller;

import com.codecool.apigateway.model.Product;
import com.codecool.apigateway.service.ProductServiceCaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
