package com.codecool.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponsePackage {
    private boolean isSuccesful;
    private String message;
}