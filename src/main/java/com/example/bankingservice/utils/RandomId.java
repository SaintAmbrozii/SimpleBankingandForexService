package com.example.bankingservice.utils;

public class RandomId {

    public static Integer randomId(){
        int leftLimit = 10000000;
        int rightLimit = 999999;
        return leftLimit + (int) (Math.random() * (rightLimit - leftLimit));
    }
}
