package com.example.bankingservice.JWT;

import lombok.Getter;
import lombok.Setter;


public class RefreshJWTRequest {
    public String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
