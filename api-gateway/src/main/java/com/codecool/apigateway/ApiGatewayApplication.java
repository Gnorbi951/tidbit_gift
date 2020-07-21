package com.codecool.apigateway;

import com.codecool.apigateway.entity.UserEntity;
import com.codecool.apigateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2
@EnableEurekaClient
public class ApiGatewayApplication {

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/userservice/**"))
                .build();
    }

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            zuulProperties.getRoutes().values().stream()
                    .forEach(route -> resources
                            .add(createResource(route.getServiceId(), route.getServiceId(), "version1")));
            return resources;
        };
    }

    private SwaggerResource createResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation("/" + location +"/v2/api-docs");
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {

            UserEntity user = UserEntity.builder()
                    .name("Lajos")
                    .password("password")
                    .roles(Arrays.asList("USER","ADMIN"))
                    .email("lajos@lajos.com")
                    .build();

            repository.save(user);

        };
    }
}
