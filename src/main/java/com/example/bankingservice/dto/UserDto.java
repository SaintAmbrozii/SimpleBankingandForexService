package com.example.bankingservice.dto;

import com.example.bankingservice.domain.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String lastname;
    private String password;
    private Boolean isAktive;


}
