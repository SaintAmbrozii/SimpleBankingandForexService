package com.example.bankingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BondRequest {
    private String ticker;
    private Double price;
    private String name;
    private Integer value;
}
