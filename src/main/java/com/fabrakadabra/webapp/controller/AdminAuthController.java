package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.dto.AdminLoginRequest;
import com.fabrakadabra.webapp.dto.AdminRegisterRequest;
import com.fabrakadabra.webapp.dto.AuthenticationResponse;
import com.fabrakadabra.webapp.service.AdminAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adminauth")
@AllArgsConstructor
public class AdminAuthController {
    private final AdminAuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AdminRegisterRequest adminRegisterRequest){
        authService.signup(adminRegisterRequest);
        return new ResponseEntity<>("Admin Registration Succesful", HttpStatus.OK);
    }

    @GetMapping("/adminVerification/{token}")
    public ResponseEntity<String> verifyAdmin(@PathVariable String token){
        authService.verifyAdmin(token);
        return new ResponseEntity<>("Admin account Activated Succesfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AdminLoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
