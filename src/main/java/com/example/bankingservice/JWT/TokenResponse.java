package com.example.bankingservice.JWT;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class TokenResponse {
    private final String type = "Bearer";
    private String acesstoken;
    private String refreshtoken;

    public TokenResponse(String acesstoken, String refreshtoken) {
        this.acesstoken = acesstoken;
        this.refreshtoken = refreshtoken;
    }


}
