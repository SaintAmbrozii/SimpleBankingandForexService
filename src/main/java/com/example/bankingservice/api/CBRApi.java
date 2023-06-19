package com.example.bankingservice.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cbrApi",url = "https://www.cbr-xml-daily.ru/daily_eng.xml")
public interface CBRApi {

    @GetMapping
    String getRatesByCbr();
}
