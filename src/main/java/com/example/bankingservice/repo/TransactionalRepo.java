package com.example.bankingservice.repo;

import com.example.bankingservice.domain.Account;
import com.example.bankingservice.domain.Transactional;
import com.example.bankingservice.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionalRepo extends CrudRepository<Transactional,Long> {




}
