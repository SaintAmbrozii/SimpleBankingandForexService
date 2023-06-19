package com.example.bankingservice.controller;

import com.example.bankingservice.JWT.JWTRequest;
import com.example.bankingservice.JWT.JWTResponse;
import com.example.bankingservice.JWT.RefreshJWTRequest;
import com.example.bankingservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest authrequest) throws AuthException {
        final JWTResponse token;
        token = authService.login(authrequest);
        return ResponseEntity.ok(token);
    }
    @PostMapping("token")
    public ResponseEntity<JWTResponse> getNewAcessToken(@RequestBody RefreshJWTRequest request) throws AuthException {
        final JWTResponse token = authService.getAcessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
    @PostMapping("refresh")
    public ResponseEntity<JWTResponse> getNewRefreshToken(@RequestBody RefreshJWTRequest request) throws AuthException {
        final JWTResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}
