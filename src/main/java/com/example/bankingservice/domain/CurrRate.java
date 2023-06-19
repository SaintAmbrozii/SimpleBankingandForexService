package com.example.bankingservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;


@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrRate {
    private String numCode;
    private String charCode;
    private String nominal;
    private String name;
    private Double value;

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public CurrRate(String numCode, String charCode, String nominal, String name, Double value) {
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "CurrencyNominalRate{" +
                "numCode='" + numCode + '\'' +
                ", charCode='" + charCode + '\'' +
                ", nominal='" + nominal + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
