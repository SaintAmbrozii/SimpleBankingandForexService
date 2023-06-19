package com.example.bankingservice.JWT;

import com.example.bankingservice.domain.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class JWTUtils {
    public static JWTAutentification generate(Claims claims) {
        final JWTAutentification jwtInfoToken = new JWTAutentification();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setEmail(claims.get("Email", String.class));
        jwtInfoToken.setEmail(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
