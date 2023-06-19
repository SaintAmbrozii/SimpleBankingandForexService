package com.example.bankingservice.controller;

import com.example.bankingservice.domain.Account;
import com.example.bankingservice.domain.User;
import com.example.bankingservice.dto.AccDto;
import com.example.bankingservice.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public void createAccount(@RequestBody Account account, @AuthenticationPrincipal User user) {
        accountService.createAccount(account,user);
    }
  //  @GetMapping("{id}")
  //  public Account findById(@PathVariable(name = "id") Long id) {
 //     return  accountService.findByID(id).orElseThrow();
//    }

    @DeleteMapping("{id}")
    public void deleteAccount(@PathVariable(name = "id") Account account) {
        accountService.deleteAccount(account);
    }

    @GetMapping
    public List<Account> allAccounts() {
        return accountService.allAccounts();
    }

    @PostMapping("{accountNumber}")
    public Account putBalance(@PathVariable(name = "accountNumber") Integer number,
                              @RequestBody AccDto dto) {
        return accountService.putBalance(number, dto.getSumma());}
    @GetMapping("{accountNumber}")
    public Account findByNumber(@PathVariable(name = "accountNumber") Integer number) {
        return accountService.findByNumber(number);
    }
    @GetMapping("/received/{accountNumber}")
    public Account receiverBalance(@PathVariable(name = "accountNumber")Integer number,@RequestBody Account account) {
        return accountService.receivedBalance(number,account);
    }


}
