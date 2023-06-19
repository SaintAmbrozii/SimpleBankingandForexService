package com.example.bankingservice.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "moexApi",url = "https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR/securities.xml?iss.meta=off&iss.only=marketdata&marketdata.columns=SECID,LAST")
public interface MoexApi {

    @GetMapping
    String getByMoex();
}

