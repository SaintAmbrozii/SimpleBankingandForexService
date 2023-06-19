package com.example.bankingservice.JWT;

import lombok.AllArgsConstructor;
import lombok.Getter;


public class JWTResponse {
    private final String type = "Bearer";
    private String acesstoken;
    private String refreshtoken;

    public JWTResponse(String acesstoken, String refreshtoken) {
        this.acesstoken = acesstoken;
        this.refreshtoken = refreshtoken;
    }

    public String getType() {
        return type;
    }

    public String getAcesstoken() {
        return acesstoken;
    }

    public void setAcesstoken(String acesstoken) {
        this.acesstoken = acesstoken;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }
}
