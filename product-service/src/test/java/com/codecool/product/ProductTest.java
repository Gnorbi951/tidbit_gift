package com.codecool.product;

import com.codecool.product.entity.Product;
import com.codecool.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@ActiveProfiles("test")
class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProductSimple() {
        Product testProduct = Product.builder()
                .name("Camera")
                .userId(2L)
                .description("Test it")
                .build();

        productRepository.save(testProduct);
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1);
    }
}