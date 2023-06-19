package com.example.bankingservice.dto;

import com.example.bankingservice.domain.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CurrencyBalance {
    private Long id;
    private Currency currency;
    private BigDecimal amountCurrency;
    private LocalDateTime localDateTime;
}
