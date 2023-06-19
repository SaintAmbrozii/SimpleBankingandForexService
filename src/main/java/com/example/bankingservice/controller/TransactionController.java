package com.example.bankingservice.controller;

import com.example.bankingservice.domain.Transactional;
import com.example.bankingservice.dto.ExchangeRequest;
import com.example.bankingservice.dto.SaleOrderDto;
import com.example.bankingservice.dto.TransactionRequest;
import com.example.bankingservice.service.TransactionalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/transactions")
@AllArgsConstructor
public class TransactionController {
    private final TransactionalService service;

    @PostMapping()
    public Transactional transactionBetweenAccount(@RequestBody TransactionRequest transactionRequest)
    {
       return service.createTransactional(transactionRequest);
    }
    @GetMapping()
    public List <Transactional> getAll(){
      return  service.getAllTransaction();
    }

    @GetMapping("{id}")
    public Optional<Transactional> findById(@PathVariable(name = "id") Long id) {
       return service.findById(id);
    }

    @PostMapping("buy/{id}")
    public void buyOrder(@PathVariable(name = "id")Integer num, @RequestBody ExchangeRequest request) {
        service.buyOrder(num, request);
    }
    @PostMapping("sale/{id}/{orderId}")
    public void saleOrder(@PathVariable(name = "id")Integer num,@PathVariable(name = "orderId" ) Long orderId) {
        service.saleOrder(num,orderId);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id")Transactional transactional) {
        service.deleteTransactional(transactional);
    }



}
