package com.example.bankingservice.service;


import com.example.bankingservice.JWT.JwtTokenGenerator;
import com.example.bankingservice.JWT.LoginRequest;
import com.example.bankingservice.JWT.TokenResponse;
import com.example.bankingservice.domain.User;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtTokenGenerator tokenGenerator;

    private final CustomUserDedailService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    @Autowired
    private AuthenticationManager authenticationManager;




    public TokenResponse authenticate(@NonNull LoginRequest request) throws AuthException {

        var authentifitation= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentifitation);


        User user = (User) userService.loadUserByUsername(request.getEmail());
        //       user= (User) authentifitation.getPrincipal();
        final String jwtToken = tokenGenerator.generateToken(user);
        final String refreshToken = tokenGenerator.generateRefreshToken(user);
        refreshStorage.put(user.getEmail(), refreshToken);
        return TokenResponse.builder().acesstoken(jwtToken).refreshtoken(refreshToken).build();

    }

    public TokenResponse getRefreshToken(@NonNull String refreshToken) throws AuthException {
        final Claims claims = tokenGenerator.extractAllClaims(refreshToken);
        final String email = claims.getSubject();
        UserDetails userDetails = userService.loadUserByUsername(email);
        if (tokenGenerator.validateToken(refreshToken, userDetails)) {
            final String AuthorizeToken = refreshStorage.get(email);
            if (AuthorizeToken != null && AuthorizeToken.equals(refreshToken)) {
                User user = (User) userService.loadUserByUsername(email);
                final String jwtToken = tokenGenerator.generateToken(user);
                final String newRefreshToken = tokenGenerator.generateRefreshToken(user);
                refreshStorage.put(user.getEmail(), newRefreshToken);
                return TokenResponse.builder().acesstoken(jwtToken).refreshtoken(newRefreshToken).build();

            }
        }

        throw new AuthException("Невалидный JWT токен");
    }

    public TokenResponse getToken(@NonNull String refreshToken) throws AuthException {
        final Claims claims = tokenGenerator.extractAllClaims(refreshToken);
        final String email = claims.getSubject();
        UserDetails userDetails = userService.loadUserByUsername(email);
        if (tokenGenerator.validateToken(refreshToken, userDetails)) {
            final String AuthorizeToken = refreshStorage.get(email);
            if (AuthorizeToken != null && AuthorizeToken.equals(refreshToken)) {
                User user = (User) userService.loadUserByUsername(email);
                final String token = tokenGenerator.generateToken(user);
                refreshStorage.put(token, null);
                return TokenResponse.builder().acesstoken(token).refreshtoken(null).build();
            }
        }
        return TokenResponse.builder().refreshtoken(null).refreshtoken(null).build();
    }
}
