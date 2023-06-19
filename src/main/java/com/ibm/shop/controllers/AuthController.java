package com.ibm.shop.controllers;

import com.ibm.shop.data.vo.JWTAuthResponse;
import com.ibm.shop.data.vo.LoginDTO;
import com.ibm.shop.data.vo.RegisterDTO;
import com.ibm.shop.services.AuthService;
import com.ibm.shop.utils.MediaType;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON)
public class AuthController {

    @Autowired
    private AuthService authService;

    // Login Rest API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDto) {
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Register Rest API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDto) {
        String response = authService.register(registerDto);

        return ResponseEntity.ok(response);
    }

}
