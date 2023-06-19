package com.example.bankingservice.service;

import com.example.bankingservice.api.XMLCBRParser;
import com.example.bankingservice.domain.*;
import com.example.bankingservice.dto.ExchangeRequest;
import com.example.bankingservice.dto.SaleOrderDto;
import com.example.bankingservice.dto.TransactionRequest;
import com.example.bankingservice.repo.AccountRepo;
import com.example.bankingservice.repo.OrderRepo;
import com.example.bankingservice.repo.TransactionalRepo;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionalService {
    private final TransactionalRepo transactionalRepo;
    private final AccountRepo accountRepo;
    private final OrderRepo orderRepo;
    private final XMLCBRParser parser;



    @javax.transaction.Transactional
    public void buyOrder(Integer id,ExchangeRequest exchangeRequest) {
        Account fromOrder = accountRepo.findByAccountNumber(id);
        BigDecimal paySumma = exchangeRequest.getAmount();
        Currency currency = exchangeRequest.getToCurrency();
        Map<Currency,BigDecimal> exchMap = parser.getMapRates(parser.getAllRates());
        if (exchMap.containsKey(currency)) {
            BigDecimal rateCurs = exchMap.get(currency);
            BigDecimal exchangeSum = paySumma.divide(rateCurs, RoundingMode.HALF_UP);
            fromOrder.setAccountBalance(fromOrder.getAccountBalance().subtract(paySumma));
            Order order = new Order();
            order.setAccount(fromOrder);
            order.setSumma(exchangeSum);
            order.setCurrency(currency);
            fromOrder.getOrders().add(order);
            orderRepo.save(order);
        }
        accountRepo.save(fromOrder);
    }
    @javax.transaction.Transactional
    public void buyOrderByCurrencyCourse(Integer id,ExchangeRequest exchangeRequest) {
        Account fromOrder = accountRepo.findByAccountNumber(id);
        BigDecimal paySumma = exchangeRequest.getAmount();
        Currency currency = exchangeRequest.getToCurrency();
        Map<Currency,BigDecimal> exchMap = parser.getMapRates(parser.getAllRates());
        if (exchMap.containsKey(currency)) {
            BigDecimal rateCurs = exchMap.get(currency);
            if (rateCurs.equals(exchangeRequest.getBuyCourse())) { //если обменный курс равен курсу покупки в ордере
                BigDecimal exchangeSum = paySumma.divide(rateCurs, RoundingMode.HALF_UP);
                fromOrder.setAccountBalance(fromOrder.getAccountBalance().subtract(paySumma));
                Order order = new Order();
                order.setAccount(fromOrder);
                order.setSumma(exchangeSum);
                order.setCurrency(currency);
                fromOrder.getOrders().add(order);
                orderRepo.save(order);
            }

        }
        accountRepo.save(fromOrder);
    }
    @javax.transaction.Transactional
    public void saleOrder(Integer id, Long orderId) {
        Account saleOrder = accountRepo.findByAccountNumber(id);//получаем id счета
        if (saleOrder.isAktive()) {
            Order fromAcc = orderRepo.findById(orderId).orElseThrow(); //получаем id ордера
            if (fromAcc!=null) {
                BigDecimal exchSumm = fromAcc.getSumma(); //получаем колличество купленной валюты
                Currency orderCurrency = fromAcc.getCurrency(); //получаем вид купленной валюты
                Map<Currency,BigDecimal> exchMap = parser.getMapRates(parser.getAllRates()); //ключ значечение с валютой и ценой
                BigDecimal rateCurs = exchMap.get(orderCurrency);// обменный курс получаем по значению ключа т.е. валюты что дает ордер
                if (rateCurs.equals(fromAcc.getSalePrice())) { //если курс продажи ордера равен курсу валют
                    BigDecimal saleCurrencyToAccountValute = exchSumm.multiply(rateCurs); //итого получаем колличество валюты ордера помноженное на курс обмена
                    saleOrder.setAccountBalance(saleOrder.getAccountBalance().add(saleCurrencyToAccountValute)); //сумму ложим на баланс
                    saleOrder.getOrders().remove(fromAcc); //ордер из списка удаляем в аккаунте
                    orderRepo.delete(fromAcc);                // в репозитории тоже трем
                }
            }
        }
        accountRepo.save(saleOrder); //сохраняем счет с новым балансом
    }
    @javax.transaction.Transactional
    public void saleOrderByCurrencyCourse(Integer id, Long orderId) {
        Account saleOrder = accountRepo.findByAccountNumber(id);//получаем id счета
        if (saleOrder.isAktive()) {
            Order fromAcc = orderRepo.findById(orderId).orElseThrow(); //получаем id ордера
            if (fromAcc!=null) {
                BigDecimal exchSumm = fromAcc.getSumma(); //получаем колличество купленной валюты
                Currency orderCurrency = fromAcc.getCurrency(); //получаем вид купленной валюты
                Map<Currency,BigDecimal> exchMap = parser.getMapRates(parser.getAllRates()); //ключ значечение с валютой и ценой
                BigDecimal rateCurs = exchMap.get(orderCurrency);// обменный курс получаем по значению ключа т.е. валюты что дает ордер
                BigDecimal saleCurrencyToAccountValute = exchSumm.multiply(rateCurs); //итого получаем колличество валюты ордера помноженное на курс обмена
                saleOrder.setAccountBalance(saleOrder.getAccountBalance().add(saleCurrencyToAccountValute)); //сумму ложим на баланс
                saleOrder.getOrders().remove(fromAcc); //ордер из списка удаляем в аккаунте
                orderRepo.delete(fromAcc); // в репозитории тоже трем
            }

        }
        accountRepo.save(saleOrder); //сохраняем счет с новым балансом
    }





    @javax.transaction.Transactional
    public Transactional createTransactional(TransactionRequest transactionRequest) {
       Integer to = transactionRequest.getToAccount();
       Integer from = transactionRequest.getFromAccount();
       BigDecimal summa = transactionRequest.getAmount();
       Account fromAcc = accountRepo.findByAccountNumber(from);
       Account toAcc = accountRepo.findByAccountNumber(to);
        if (toAcc!=null&&fromAcc!=null);
        assert fromAcc != null;
        if (fromAcc.getAccountBalance().compareTo(BigDecimal.ONE) == 1
                && fromAcc.getAccountBalance().compareTo(summa) ==1)
        {
            fromAcc.setAccountBalance(fromAcc.getAccountBalance().subtract(summa));
            accountRepo.save(fromAcc);
            assert toAcc != null;
            toAcc.setAccountBalance(toAcc.getAccountBalance().add(summa));
            accountRepo.save(toAcc);
        }
        Transactional transactional = Transactional.builder()
                .amount(summa).fromAccount(fromAcc).toAccount(toAcc).dateTime(LocalDateTime.now()).build();
      return  transactionalRepo.save(transactional);

    }

    @javax.transaction.Transactional
    public void deleteTransactional(Transactional transactional){
        transactionalRepo.delete(transactional);
    }

    public List<Transactional> getAllTransaction(){
        return (List<Transactional>) transactionalRepo.findAll();
    }
    public Optional<Transactional> findById(Long id) {
        return transactionalRepo.findById(id);
    }





}
