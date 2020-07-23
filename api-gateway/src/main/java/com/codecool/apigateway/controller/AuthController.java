package com.codecool.apigateway.controller;

import com.codecool.apigateway.entity.UserEntity;
import com.codecool.apigateway.model.ResponseType;
import com.codecool.apigateway.model.UserCredentials;
import com.codecool.apigateway.service.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ControllerUtil controllerUtil;
    private final UserUtils userUtils;

    @PostMapping(value = "/login")
    public ResponseEntity signIn(@RequestBody UserCredentials data) {
        return userUtils.signInUser(data);
    }

    @PostMapping(value = "/register")
    public ResponseType registration(@RequestBody UserEntity data) {
        String response = userUtils.registerUser(data);
        return new ResponseType(response);
    }

    @GetMapping("/get-user-from-token")
    public UserEntity returnUserFromToken(@RequestHeader String Authorization) {
        return controllerUtil.getUserFromToken(Authorization);
    }
}
