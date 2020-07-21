package com.codecool.apigateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private Long price;
    private String picture;
}
