package com.example.bankingservice.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountTest {

    @Test
    void GetAccount() {
        Account account = new Account(1L,1,true, AccountType.PRIMARY.name(), BigDecimal.valueOf(10000), new User(),Currency.RUB);
    }

    @Test
    void getId() {
    }

    @Test
    void getAccountNumber() {
    }

    @Test
    void isAktive() {
    }

    @Test
    void getAccountType() {
    }

    @Test
    void getAccountBalance() {
    }

    @Test
    void getUser() {
    }

    @Test
    void setId() {
    }

    @Test
    void setAccountNumber() {
    }

    @Test
    void setAktive() {
    }

    @Test
    void setAccountType() {
    }

    @Test
    void setAccountBalance() {
    }

    @Test
    void setUser() {
    }
}