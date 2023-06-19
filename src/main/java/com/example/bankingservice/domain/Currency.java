package com.example.bankingservice.domain;

public enum Currency {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    HKD("HKD"),
    CHF("CHF"),
    JPY("JPY"),
    CNY("CNY"),
    TRY("TRY"),
    AUD("AUD"),
    ZAR("ZAR"),
    KRW("KRW"),
    SEK("SEK"),
    CZK("CZK"),
    UAH("UAH"),
    UZS("UZS"),
    TMT("TMT"),
    TJS("TJS"),
    THB("THB"),
    SGD("SJD"),
    XDR("SDR"),
    RON("RON"),
    PLN("PLN"),
    NOK("NOK"),
    MDL("MDL"),
    KGS("KGS"),
    CAD("CAD"),
    KZT("KZT"),
    INR("INR"),
    DKK("DKK"),
    NZD("NZD"),
    HUF("HUF"),
    BRL("BRL"),
    RSD("RSD"),
    BGN("BGN"),
    BYN("BYN"),
    AMD("AMD"),
    VND("VND"),
    GEL("GEL"),
    AED("AED"),
    EGP("EGP"),
    IDR("IDR"),
    QAR("QAR"),
    AZN("AZN");




    private String currency;

    Currency(String currency) {
    }

    void Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
