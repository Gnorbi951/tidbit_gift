package com.codecool.apigateway.service;

import com.codecool.apigateway.entity.UserEntity;
import com.codecool.apigateway.model.Product;
import com.codecool.apigateway.model.ProductListWrapper;
import com.codecool.apigateway.model.ResponsePackage;
import com.codecool.apigateway.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ProductServiceCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

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

    public ResponsePackage addNewProduct(Product product) {
        UserEntity user = userRepository.getUserById(product.getUserId());

        if (user != null) {
            return restTemplate.postForObject(baseUrl + "/product/", product, ResponsePackage.class);
        }
        return new ResponsePackage(false, "Wrong user");
    }
}
