package com.example.bankingservice.controller;

import com.example.bankingservice.JWT.LoginRequest;
import com.example.bankingservice.JWT.TokenResponse;
import com.example.bankingservice.JWT.RefreshJWTRequest;
import com.example.bankingservice.domain.User;
import com.example.bankingservice.dto.UserDto;
import com.example.bankingservice.service.AuthService;
import com.example.bankingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest authrequest) throws AuthException {
        final TokenResponse token;
        token = authService.authenticate(authrequest);
        return ResponseEntity.ok(token);
    }
    @PostMapping("token")
    public ResponseEntity<TokenResponse> getNewAcessToken(@RequestBody RefreshJWTRequest request) throws AuthException {
        final TokenResponse token = authService.getToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
    @PostMapping("refresh")
    public ResponseEntity<TokenResponse> getNewRefreshToken(@RequestBody RefreshJWTRequest request) throws AuthException {
        final TokenResponse token = authService.getRefreshToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
    @PostMapping(value = "register",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

}
