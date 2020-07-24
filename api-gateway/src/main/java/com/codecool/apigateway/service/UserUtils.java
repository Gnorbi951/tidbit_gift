package com.codecool.apigateway.service;

import com.codecool.apigateway.entity.UserEntity;
import com.codecool.apigateway.model.UserCredentials;
import com.codecool.apigateway.repository.UserRepository;
import com.codecool.apigateway.security.JwtTokenServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenServices jwtTokenServices;
    private final AuthenticationManager authenticationManager;
    private final UserService service;

    public String registerUser(UserEntity data) {
        String userName = data.getName();
        String password = data.getPassword();
        String email = data.getEmail();

        // Linked list validation chain with booleans

        // First check for invalid input
        char[] illegalCharacterList = {'.', ',', '+', '-', '[', ']', '{', '}', ' ', '"', '\'', '/', '\\'};
        if (userName == null || userName.length() < 4) return "Username is too short";
        if (userName.length() > 20) return "Username is too long";
        if (password == null || password.length() < 4) return "Password is too short";
        if (password.length() > 20) return "Password is too long";
        if (Character.isDigit(userName.charAt(0))) return "First character can't be a number";

        for (char c : illegalCharacterList) {
            for (int i=0; i<userName.length(); i++) {
                if (userName.charAt(i) == c) return "Username contains illegal characters";
            }
        }

        List<UserEntity> userList = userRepository.findAll();
        for (UserEntity hotelUser : userList) {
            if (hotelUser.getName().equals(userName)) {
                return "Username already taken";
            }
        }

        UserEntity user = UserEntity.builder()
                .name(userName)
                .password(passwordEncoder.encode(password))
                .email(email)
                .roles(Collections.singletonList("USER"))
                .build();

        userRepository.save(user);

        return "Successful Registration";

    }

    public ResponseEntity signInUser(UserCredentials data) {
        Map<Object, Object> model = new HashMap<>();
        try {
            String name = data.getName();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .parallelStream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(name, roles);
            Long id = service.getIdByName(name);

            model.put("id",id);
            model.put("name", name);
            model.put("roles", roles);
            model.put("token", token);
            model.put("status","OK");
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            model.put("status", "WRONG");
            return ResponseEntity.ok(model);
        }
    }
}