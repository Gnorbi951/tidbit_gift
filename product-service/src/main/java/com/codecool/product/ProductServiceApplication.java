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
                    .userId(2L)
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
            Product bagSuit = Product.builder()
                    .userId(3L)
                    .name("Garbage Bag Suit")
                    .description("I bought a new set of tires for my wife's birthday so she bought me this")
                    .picture("https://bit.ly/2CYQBG6")
                    .price(150L)
                    .build();
            Product fakeMoustache = Product.builder()
                    .userId(3L)
                    .name("Fake Moustache")
                    .description("Why?")
                    .picture("https://i.ebayimg.com/images/g/ahIAAOSw8vZXM7Nz/s-l640.jpg")
                    .price(2000L)
                    .build();
            Product retroPhone = Product.builder()
                    .userId(4L)
                    .name("Retro Phone")
                    .description("I don't have a jack cable so it's useless. (Isn't it anyway?)")
                    .picture("https://bit.ly/3fRLq9G")
                    .price(2399L)
                    .build();
            Product babyHands = Product.builder()
                    .userId(2L)
                    .name("Plastic Baby hands")
                    .description("I have like 20 of these, I need help.")
                    .picture("https://ae01.alicdn.com/kf/H2a2953e26aa44c8db41acbc638b6f1767.jpg")
                    .price(1200L)
                    .build();






            productRepository.saveAll(Arrays.asList(nikonCamera, hat, toilet, bagSuit,
                    fakeMoustache, retroPhone, babyHands));
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
