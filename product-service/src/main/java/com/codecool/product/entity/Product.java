package com.codecool.product.entity;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;
    private Long price;
    private String picture;

}
