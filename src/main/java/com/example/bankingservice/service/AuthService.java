package com.example.bankingservice.service;

import com.example.bankingservice.JWT.JWTAutentification;
import com.example.bankingservice.JWT.JWTProvider;
import com.example.bankingservice.JWT.JWTRequest;
import com.example.bankingservice.JWT.JWTResponse;
import com.example.bankingservice.domain.User;
import com.sun.istack.NotNull;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JWTProvider jwtProvider;

    public JWTResponse login(@NotNull JWTRequest authRequest) throws AuthException {
        final User user = userService.getByEmail(authRequest.getEmail())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String acessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateAccessToken(user);
            refreshStorage.put(user.getEmail(), refreshToken);
            return new JWTResponse(acessToken, refreshToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }

    }

    public JWTResponse getAcessToken(@NotNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(email);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String acessToken = jwtProvider.generateAccessToken(user);
                return new JWTResponse(acessToken, null);
            }
        }
        return new JWTResponse(null, null);

    }
    public JWTResponse refresh(@NotNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(email);
            if (saveRefreshToken!=null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String acessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getEmail(), newRefreshToken);
                return new JWTResponse(acessToken,newRefreshToken);
            }
        }
         throw new AuthException("Невалидный JWT токен");
    }
    public JWTAutentification getAuthInfo() {
        return (JWTAutentification) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
