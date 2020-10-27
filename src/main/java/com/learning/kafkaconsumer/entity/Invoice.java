package com.learning.kafkaconsumer.entity;

public class Invoice {
    private String number;
    private double amount;
    private String currency;

    public Invoice() {
    }

    public Invoice(String number, double amount, String currency) {
        this.number = number;
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getNumber() {
        return number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "number='" + number + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
