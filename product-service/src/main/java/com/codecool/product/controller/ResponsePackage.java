package com.codecool.product.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponsePackage {
    private boolean isSuccesful;
    private String message;
}