package com.example.bankingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class BankingserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingserviceApplication.class, args);
    }

}
