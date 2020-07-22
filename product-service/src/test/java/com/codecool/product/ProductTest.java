package com.codecool.product;

import com.codecool.product.entity.Product;
import com.codecool.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
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

    @Test
    public void testFindAllForManyProducts() {
        Product testProduct1 = Product.builder()
                .name("Camera")
                .userId(2L)
                .description("Test it")
                .build();

        Product testProduct2 = Product.builder()
                .name("Camera2")
                .userId(1L)
                .description("Test it2")
                .build();

        Product testProduct3 = Product.builder()
                .name("Camera3")
                .userId(3L)
                .description("Test it3")
                .build();

        productRepository.saveAll(Arrays.asList(testProduct1, testProduct2, testProduct3));
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(3);
    }


    @Test
    public void testNameCannotBeNull() {
        Product testProduct = Product.builder()
                .userId(2L)
                .description("Test it")
                .build();

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            productRepository.save(testProduct);
            productRepository.findAll();
        });
    }

    @Test
    public void testUserIdCannotBeNull() {
        Product testProduct = Product.builder()
                .name("test name")
                .description("Test it")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            productRepository.save(testProduct);
            productRepository.findAll();
        });
    }

    @Test
    public void testDescriptionCannotBeNull() {
        Product testProduct = Product.builder()
                .userId(2L)
                .name("test name")
                .build();

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            productRepository.save(testProduct);
            productRepository.findAll();
        });
    }


}