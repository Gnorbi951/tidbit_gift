package com.codecool.apigateway.controller;

import com.codecool.apigateway.entity.UserEntity;
import com.codecool.apigateway.model.ResponseType;
import com.codecool.apigateway.model.UserCredentials;
import com.codecool.apigateway.security.JwtTokenServices;
import com.codecool.apigateway.service.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;
    private final ControllerUtil controllerUtil;

    private final UserUtils userUtils;


    @PostMapping(value = "/login")
    public ResponseEntity signIn(@RequestBody UserCredentials data) {
        Map<Object, Object> model = new HashMap<>();
        try {
            String username = data.getUsername();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            model.put("status","OK");
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            model.put("status", "WRONG");
            return ResponseEntity.ok(model);
        }
    }

    @PostMapping(value = "/register")
    public ResponseType registration(@RequestBody UserEntity data) {
        String response = userUtils.registerUser(data);
        return new ResponseType(response);
    }

    @GetMapping("/get-user-from-token")
    public UserEntity returnHotelUserFromToken(@RequestHeader String Authorization) {
        return controllerUtil.getUserFromToken(Authorization);
    }
}
