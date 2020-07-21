package com.codecool.apigateway.service;

import com.codecool.apigateway.model.Product;
import com.codecool.apigateway.model.ProductListWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ProductServiceCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${productservice.url}")
    private String baseUrl;

    public List<Product> getAllProducts() {
        return restTemplate.getForObject(baseUrl + "/product", List.class);
    }

    public Product getById(Long id) {
        return restTemplate.getForObject(baseUrl + "/product/" + id, Product.class);
    }

    public List<Product> getAllByUserId(Long id) {
        return restTemplate.getForObject(baseUrl + "/product/user/" + id, List.class);
    }
}
