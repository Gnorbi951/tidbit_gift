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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    public void testGetProductById() {
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
    public void testGetById() {
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
        Product expectedProduct  = products.get(0);
        Product actualProduct = productRepository.getById(expectedProduct.getId());
        assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    @Test
    public void testGetAllByUserId() {
        Product testProduct1 = Product.builder()
                .name("Camera")
                .userId(1L)
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
        List<Product> products = productRepository.getAllByUserId(1L);
        assertThat(products).hasSize(2);
    }

    @Test
    public void testGetByIdAndUserId() {
        Product testProduct1 = Product.builder()
                .name("Camera")
                .userId(1L)
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
        Product expectedProduct  = products.get(0);
        Product actualProduct = productRepository.getByIdAndUserId(expectedProduct.getId(), expectedProduct.getUserId());
        assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    @Test
    public void testWrongGetByIdAndUserIdIsNull() {
        Product testProduct1 = Product.builder()
                .name("Camera")
                .userId(1L)
                .description("Test it")
                .build();

        Product testProduct2 = Product.builder()
                .name("Camera2")
                .userId(2L)
                .description("Test it2")
                .build();

        Product testProduct3 = Product.builder()
                .name("Camera3")
                .userId(3L)
                .description("Test it3")
                .build();

        productRepository.saveAll(Arrays.asList(testProduct1, testProduct2, testProduct3));

        List<Product> products = productRepository.findAll();
        Product actualProduct = productRepository.getByIdAndUserId(products.get(0).getId(), products.get(1).getUserId());
        assertThat(actualProduct).isEqualTo(null);
    }

    @Test
    public void testDeleteByIdAndUserIdGetsErrorCodeIfIncorrectData() {
        Product testProduct1 = Product.builder()
                .name("Camera")
                .userId(1L)
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

        List<Product> originalProducts = productRepository.findAll();
        Product expectedProduct  = originalProducts.get(0);
        int isDeleted = productRepository.deleteByIdAndUserId(expectedProduct.getId(), expectedProduct.getUserId());
        assertThat(isDeleted).isEqualTo(1);
    }

    @Test
    public void testDeleteByIdAndUserIdGetsErrorCodeIfNotCorrect() {
        Product testProduct1 = Product.builder()
                .name("Camera")
                .userId(1L)
                .description("Test it")
                .build();

        Product testProduct2 = Product.builder()
                .name("Camera2")
                .userId(2L)
                .description("Test it2")
                .build();

        Product testProduct3 = Product.builder()
                .name("Camera3")
                .userId(3L)
                .description("Test it3")
                .build();

        productRepository.saveAll(Arrays.asList(testProduct1, testProduct2, testProduct3));

        List<Product> originalProducts = productRepository.findAll();
        int isDeleted = productRepository.deleteByIdAndUserId(originalProducts.get(0).getId(), originalProducts.get(1).getUserId());
        assertThat(isDeleted).isEqualTo(0);
    }

    @Test
    public void testDeleteByIdAndUserIdShrinkDataset() {
        Product testProduct1 = Product.builder()
                .name("Camera")
                .userId(1L)
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

        List<Product> originalProducts = productRepository.findAll();
        assertThat(originalProducts).hasSize(3);
        productRepository.deleteByIdAndUserId(originalProducts.get(0).getId(), originalProducts.get(0).getUserId());
        List<Product> modifiedProducts = productRepository.findAll();
        assertThat(modifiedProducts).hasSize(2);
    }

    @Test
    public void testNullNameThrowsError() {
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
    public void testNoUserIdThrowsError() {
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
    public void testNoDescriptionThrowsError() {
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