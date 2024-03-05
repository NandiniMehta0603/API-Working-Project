package com.finance.transactions.kirana;

public class GetKiranaDto {
    public int amount;
    public String type_of_trans;

    public GetKiranaDto() {
    }

    public GetKiranaDto(int amount, String type_of_trans) {
        this.amount = amount;
        this.type_of_trans = type_of_trans;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType_of_trans() {
        return type_of_trans;
    }

    public void setType_of_trans(String type_of_trans) {
        this.type_of_trans = type_of_trans;
    }
}
