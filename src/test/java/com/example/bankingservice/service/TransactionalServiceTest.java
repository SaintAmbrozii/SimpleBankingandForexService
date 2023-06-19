package com.example.bankingservice.service;

import com.example.bankingservice.domain.*;
import com.example.bankingservice.repo.AccountRepo;
import com.example.bankingservice.repo.TransactionalRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.math.BigDecimal;
import java.util.List;

class TransactionalServiceTest {


    @Mock
   TransactionalRepo transactionalRepo;
    @Mock
    AccountRepo accountRepo;

    @BeforeEach
    void Start() {
        transactionalRepo = Mockito.mock(TransactionalRepo.class);
        accountRepo = Mockito.mock(AccountRepo.class);
    }


    @Test
    void createTransactional() {
        Account from = new Account(1L,1,true, AccountType.PRIMARY.name(), BigDecimal.valueOf(10000), new User(),Currency.RUB);
        Account to = new Account(2L,2,true,AccountType.FOREX.name(), BigDecimal.valueOf(10000), new User(),Currency.RUB);
        BigDecimal summa = BigDecimal.valueOf(3000);
        Transactional transactional = Transactional.builder().fromAccount(from).toAccount(to).amount(summa).build();
        from.setAccountBalance(from.getAccountBalance().subtract(summa));
        accountRepo.save(from);
        to.setAccountBalance(to.getAccountBalance().add(summa));
        accountRepo.save(to);
        transactionalRepo.save(transactional);
        Assertions.assertEquals(BigDecimal.valueOf(7000),from.getAccountBalance());
        Assertions.assertEquals(BigDecimal.valueOf(13000),to.getAccountBalance());
        Mockito.verify(accountRepo,Mockito.times(1)).save(from);
        Mockito.verify(accountRepo,Mockito.times(1)).save(to);
        Mockito.verify(transactionalRepo,Mockito.times(1)).save(transactional);
   }



    @Test
    void createSimpleTransaction() {
          Account from = new Account(1L,1,true, AccountType.PRIMARY.name(), BigDecimal.valueOf(10000), new User(), Currency.RUB);
          Account to = new Account(2L,2,true,AccountType.FOREX.name(), BigDecimal.valueOf(10000), new User(),Currency.RUB);
          BigDecimal summa = BigDecimal.valueOf(3000);
          Transactional transactional = Transactional.builder().fromAccount(from).toAccount(to).amount(summa).build();
          transactionalRepo.save(transactional);
        Assertions.assertEquals(BigDecimal.valueOf(7000),from.getAccountBalance().subtract(summa));
        Assertions.assertEquals(BigDecimal.valueOf(13000),to.getAccountBalance().add(summa));
        Mockito.verify(transactionalRepo,Mockito.times(1)).save(transactional);
    }

    @Test
    void getAllTransactions() {
       List<Transactional> transactionals = (List<Transactional>) transactionalRepo.findAll();
       Assertions.assertNotNull(transactionals);
       Mockito.verify(transactionalRepo,Mockito.times(1)).findAll();

    }

 //   @Test
 //   void findByUserId() {


 //   }

}