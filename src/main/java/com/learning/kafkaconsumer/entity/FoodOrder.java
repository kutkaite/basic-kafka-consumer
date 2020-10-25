package com.learning.kafkaconsumer.entity;

public class FoodOrder {
    private String item;
    private int amount;

    public FoodOrder() {
    }

    public FoodOrder(String item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FoodOrder{" +
                "item='" + item + '\'' +
                ", amount=" + amount +
                '}';
    }
}
