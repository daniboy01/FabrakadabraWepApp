package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.config.CrossOriginUrl;
import com.fabrakadabra.webapp.dto.AdminLoginRequest;
import com.fabrakadabra.webapp.dto.AdminRegisterRequest;
import com.fabrakadabra.webapp.dto.AuthenticationResponse;
import com.fabrakadabra.webapp.dto.RefreshTokenRequest;
import com.fabrakadabra.webapp.service.AdminAuthService;
import com.fabrakadabra.webapp.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/adminauth")
@AllArgsConstructor
public class AdminAuthController {
    private final AdminAuthService authService;
    private final RefreshTokenService refreshTokenService;

    @CrossOrigin(CrossOriginUrl.URl)
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AdminRegisterRequest adminRegisterRequest){
        authService.signup(adminRegisterRequest);
        return new ResponseEntity<>("Admin Registration Succesful", HttpStatus.OK);
    }

    @CrossOrigin(CrossOriginUrl.URl)
    @GetMapping("/adminVerification/{token}")
    public ResponseEntity<String> verifyAdmin(@PathVariable String token){
        authService.verifyAdmin(token);
        return new ResponseEntity<>("Admin account Activated Succesfully", HttpStatus.OK);
    }

    @CrossOrigin(CrossOriginUrl.URl)
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AdminLoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @CrossOrigin(CrossOriginUrl.URl)
    @PostMapping("refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }

    @CrossOrigin(CrossOriginUrl.URl)
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Succesfully!!");
    }
}
