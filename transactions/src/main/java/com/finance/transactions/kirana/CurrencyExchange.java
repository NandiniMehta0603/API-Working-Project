package com.finance.transactions.kirana;

import javax.persistence.Entity;
import java.util.Map;


public class CurrencyExchange {
    private boolean success;
    private String base;
    private Map<String,Float> rates;

    CurrencyExchange(){

    }

    public CurrencyExchange(boolean success, String base, Map<String, Float> rates) {
        this.success = success;
        this.base = base;
        this.rates = rates;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Float> getRates() {
        return rates;
    }

    public void setMp(Map<String, Float> rates) {
        this.rates = rates;
    }
}
