package com.example.bankingservice.repo;

import com.example.bankingservice.domain.Account;
import com.example.bankingservice.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AccountRepo extends CrudRepository<Account,Long> {
 Optional<Account> findById(Long id);
 Account findByAccountNumber(Integer id);
 Account findByUser(User user);

}
