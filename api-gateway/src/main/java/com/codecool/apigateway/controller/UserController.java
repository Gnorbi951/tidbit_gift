package com.codecool.apigateway.controller;

import com.codecool.apigateway.entity.UserEntity;
import com.codecool.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userservice")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public List<UserEntity> getAllUser(){
        return service.getAllUser();
    }

    @GetMapping("/user/{id}")
    public UserEntity getUserById(@PathVariable("id")Long id){
        return service.getUserById(id);
    }

    public String getPasswordById(@PathVariable("id")Long id){
        return service.getPasswordById(id);
    }
}
