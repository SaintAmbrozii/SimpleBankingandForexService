package com.example.bankingservice.repo;

import com.example.bankingservice.domain.Account;
import com.example.bankingservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);



}
