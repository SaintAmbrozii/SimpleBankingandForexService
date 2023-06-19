package com.example.bankingservice.dto;

import com.example.bankingservice.domain.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleOrderDto {
    private Long orderId;
}
