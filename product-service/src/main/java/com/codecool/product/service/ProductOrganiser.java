package com.codecool.product.service;

import com.codecool.product.model.ResponsePackage;
import com.codecool.product.entity.Product;
import com.codecool.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class ProductOrganiser {

    private final ProductRepository productRepository;

    public ResponsePackage addNewProduct(Product product) {
        if (product != null) {
            Product newProduct = Product.builder()
                    .userId(product.getUserId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .build();

            if (product.getPicture() != null) {
                newProduct.setPicture(product.getPicture());
            }

            if (product.getPrice() != null) {
                newProduct.setPrice(product.getPrice());
            }

            productRepository.save(newProduct);

            return new ResponsePackage(true, "New products saved.");
        }

        return new ResponsePackage(false, "No product detected.");
    }

    public ResponsePackage deleteProductOfUser(Long id, Long userId) {
        Product foundProduct = productRepository.getByIdAndUserId(id, userId);

        if (foundProduct != null) {
            int deletionResponse = productRepository.deleteByIdAndUserId(id, userId);
            return deletionResponse == 0 ? new ResponsePackage(false, "Error in deletion.") : new ResponsePackage(true, "Product successfully deleted.");
        }

        return new ResponsePackage(false, "Product not found.");
    }
}
