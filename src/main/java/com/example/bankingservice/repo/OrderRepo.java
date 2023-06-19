package com.example.bankingservice.repo;

import com.example.bankingservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {


}

