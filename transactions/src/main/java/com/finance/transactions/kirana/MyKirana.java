package com.finance.transactions.kirana;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="transaction")
public class MyKirana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String type_of_trans;
    public LocalDate date;
    public int amount;

    public MyKirana() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_of_trans() {
        return type_of_trans;
    }

    public void setType_of_trans(String type_of_trans) {
        this.type_of_trans = type_of_trans;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
