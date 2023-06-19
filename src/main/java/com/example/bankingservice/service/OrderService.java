package com.example.bankingservice.service;

import com.example.bankingservice.api.XMLCBRParser;
import com.example.bankingservice.domain.Currency;
import com.example.bankingservice.domain.Order;
import com.example.bankingservice.repo.OrderRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final XMLCBRParser parser;

    public OrderService(OrderRepo orderRepo, XMLCBRParser parser) {
        this.orderRepo = orderRepo;
        this.parser = parser;
    }




}
