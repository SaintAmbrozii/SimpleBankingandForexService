package com.example.bankingservice.dto;

import com.example.bankingservice.domain.Account;
import lombok.Data;

import java.util.List;
@Data
public class AccontDto {
    private String username;
    private List<Account> accounts;


}
