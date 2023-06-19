package com.example.bankingservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BondDTO {

    private String ticker;
    private Double price;

    @Override
    public String toString() {
        return "BondDTO{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                '}';
    }
}
