package com.example.bankingservice.service;

import com.example.bankingservice.domain.Account;
import com.example.bankingservice.domain.AccountType;
import com.example.bankingservice.domain.Currency;
import com.example.bankingservice.domain.User;
import com.example.bankingservice.dto.AccDto;
import com.example.bankingservice.repo.AccountRepo;
import com.example.bankingservice.repo.TransactionalRepo;
import com.example.bankingservice.repo.UserRepo;
import com.example.bankingservice.utils.RandomId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService {


    private final AccountRepo accountRepo;
    private final UserRepo userRepo;
    private final TransactionalRepo transactionalRepo;



    public void createAccount(Account account,User user) {

            account.setUser(user);
            account.setAccountBalance(BigDecimal.ZERO);
            account.setAktive(true);
            account.setAccountType(String.valueOf(AccountType.PRIMARY));
            account.setCurrency(account.getCurrency());
            account.setAccountNumber(RandomId.randomId());
            accountRepo.save(account);

    }

    public void deleteAccount(Account account) {
        accountRepo.delete(account);
    }

    public void createForexAccount(Account newAccount,User user) {
        Account current = accountRepo.findByUser(user);
        if (current!=null) {
            newAccount.setAccountBalance(BigDecimal.ZERO);
            newAccount.setAccountType(String.valueOf(AccountType.FOREX));
            newAccount.setAktive(true);
            newAccount.setUser(newAccount.getUser());
            newAccount.setCurrency(newAccount.getCurrency());
            newAccount.setAccountNumber(RandomId.randomId());
            accountRepo.save(newAccount);
        }

    }
    public List<Account> allAccounts() {
        return (List<Account>) accountRepo.findAll();
    }

    public Optional<Account> findByID(Long id) {
        return accountRepo.findById(id);
    }

    @Transactional
    public Account putBalance(Integer id, Double accDto) {
        Account inDB = accountRepo.findByAccountNumber(id);
        BigDecimal summa = BigDecimal.valueOf(accDto);
        if (inDB!=null){
            inDB.setAccountBalance(inDB.getAccountBalance().add(summa));
        }
        assert inDB != null;
        return accountRepo.save(inDB);
    }
    @Transactional
    public Account receivedBalance(Integer number,Account account) {
        Account inDB = accountRepo.findByAccountNumber(number);
        BigDecimal summa = account.getAccountBalance();
        if (inDB!=null) {
            inDB.setAccountBalance(inDB.getAccountBalance().subtract(summa));
        }
        assert inDB != null;
        return accountRepo.save(inDB);
    }
    public Account betweenAccount(Integer number,Account account) {
        Account from = accountRepo.findByAccountNumber(number);
        Account to = accountRepo.findByAccountNumber(account.getAccountNumber());
        BigDecimal summa = account.getAccountBalance();
        if (from!=null&&to!=null) {
            from.setAccountBalance(from.getAccountBalance().subtract(summa));
            to.setAccountBalance(to.getAccountBalance().add(summa));
        }
        return null;
    }


    public Account findByNumber(Integer number){
       return accountRepo.findByAccountNumber(number);
    }




}
