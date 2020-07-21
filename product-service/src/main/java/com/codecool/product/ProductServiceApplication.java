package com.codecool.product;

import com.codecool.product.entity.Product;
import com.codecool.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class ProductServiceApplication {

    private final ProductRepository productRepository;

    public ProductServiceApplication(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {

            Product nikonCamera = Product.builder()
                    .userId(1L)
                    .name("Nikon Camera")
                    .description("Nikon SLR camera from 1991.")
                    .picture("https://3.bp.blogspot.com/-cr90gnwsHfc/Wx5J8PwfKfI/AAAAAAAABSU/1ROs4-sYggUuOA7hWyGaLTu0G3_uJnL1wCLcBGAs/s1600/IMG_2666.JPG")
                    .price(15000L)
                    .build();
            Product hat = Product.builder()
                    .userId(2L)
                    .name("Sombrero Hat")
                    .description("My cousin bought me this hat in Mexico")
                    .picture("https://images-na.ssl-images-amazon.com/images/I/51PYxvqDKyL._AC_SX425_.jpg")
                    .price(1500L)
                    .build();
            Product toilet = Product.builder()
                    .userId(1L)
                    .name("iToilet")
                    .description("My dad brought me this apple product for some reason. I'm giving it for half price!")
                    .picture("https://worst-gifts.com/wp-content/uploads/2018/12/81578-txvels.jpg")
                    .price(350000L)
                    .build();



            productRepository.saveAll(Arrays.asList(nikonCamera, hat, toilet));
        };
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build();
    }

}
