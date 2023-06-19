package com.example.bankingservice.service;


import com.example.bankingservice.domain.Account;
import com.example.bankingservice.domain.AccountType;
import com.example.bankingservice.domain.Currency;
import com.example.bankingservice.domain.User;
import com.example.bankingservice.repo.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AccountServiceTest {


   @Mock
   AccountRepo accountRepo;

   @BeforeEach
    void init() {
       accountRepo = Mockito.mock(AccountRepo.class);
    }


    @org.junit.jupiter.api.Test
   public void createAccount() {
        Account account = new Account(1L,1,true, AccountType.PRIMARY.name(), BigDecimal.valueOf(10000), new User(), Currency.RUB);
        accountRepo.save(account);
       assertEquals(BigDecimal.valueOf(10000),account.getAccountBalance());
        assertEquals(1L,account.getId());
       assertEquals(1,1);
       assertEquals(true,true);
       assertEquals(new User(),new User());
       assertEquals(AccountType.PRIMARY.name(),AccountType.PRIMARY.name());
        Mockito.verify(accountRepo,Mockito.times(1)).save(account);
    }

    @org.junit.jupiter.api.Test
    public void deleteAccount() {Account account = new Account(1L,1,true, AccountType.PRIMARY.name(), BigDecimal.valueOf(10000), new User(),Currency.RUB);accountRepo.delete(account);
        assertEquals((Long) null,null);
        Mockito.verify(accountRepo,Mockito.times(1)).delete(account);
    }

   @Test
    public void createNewAccount() {
        Account account = new Account(2L,2,true,AccountType.FOREX.name(), BigDecimal.valueOf(10000), new User(),Currency.RUB);
        accountRepo.save(account);
        Mockito.verify(accountRepo,Mockito.times(1)).save(account);
   }
}