package com.codecool.apigateway.service;

import com.codecool.apigateway.controller.ControllerUtil;
import com.codecool.apigateway.entity.UserEntity;
import com.codecool.apigateway.model.Product;
import com.codecool.apigateway.model.ResponsePackage;
import com.codecool.apigateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceCaller {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final ControllerUtil controllerUtil;

    @Value("${productservice.url}")
    private String baseUrl;

    public List<Product> getAllProducts() {
        return restTemplate.getForObject(baseUrl + "/product", List.class);
    }

    public Product getById(Long id) {
        return restTemplate.getForObject(baseUrl + "/product/" + id, Product.class);
    }

    public List<Product> getAllProductsByUserId(Long id) {
        return restTemplate.getForObject(baseUrl + "/product/user/" + id, List.class);
    }

    public ResponsePackage addNewProduct(Product product, String authorization) {
        UserEntity userFromToken = controllerUtil.getUserFromToken(authorization);
        UserEntity user = userRepository.getUserById(product.getUserId());

        if (user != null && userFromToken.getId().equals(user.getId())) {
            return restTemplate.postForObject(baseUrl + "/product", product, ResponsePackage.class);
        }
        return new ResponsePackage(false, "Wrong user.");
    }

    public ResponsePackage deleteProductOfUser(Long id, String authorization) {
        UserEntity userFromToken = controllerUtil.getUserFromToken(authorization);
        Long userId = userFromToken.getId();
        UserEntity user = userRepository.getUserById(userId); // TODO: check if we need to double check at all
        String url = baseUrl + "/product/" + id + "/" + userId;

        if (user != null && userFromToken.getName() == user.getName()) {
            Product product = getById(id);
            if (product.getUserId() == user.getId()) {
                // TODO: rewrite to restTemplate.exchange() to receive feedback from Product Service
                restTemplate.delete(url);
                return new ResponsePackage(true, "Product deleted.");
            }
            return new ResponsePackage(false, "Wrong user.");
        }
        return new ResponsePackage(false, "Wrong user.");
    }

    @Data
    @AllArgsConstructor
    public class UserId {
        private Long userId;
    }
}
