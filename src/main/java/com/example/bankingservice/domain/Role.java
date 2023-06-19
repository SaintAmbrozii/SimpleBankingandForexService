package com.example.bankingservice.domain;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER("USER"),ADMIN("ADMIN");


    private final String roles;

    @Override
    public String getAuthority() {
        return roles;
    }
}
