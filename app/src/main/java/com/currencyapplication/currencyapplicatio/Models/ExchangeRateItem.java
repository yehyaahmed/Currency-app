package com.currencyapplication.currencyapplicatio.Models;

public class ExchangeRateItem {

    int id;
    String name;
    int sale;
    int buy;
    int history_value;
    String history_status;

    public ExchangeRateItem(int id, String name, int sale, int buy, int history_value, String history_status) {
        this.id = id;
        this.name = name;
        this.sale = sale;
        this.buy = buy;
        this.history_value = history_value;
        this.history_status = history_status;
    }

    public ExchangeRateItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getHistory_value() {
        return history_value;
    }

    public void setHistory_value(int history_value) {
        this.history_value = history_value;
    }

    public String getHistory_status() {
        return history_status;
    }

    public void setHistory_status(String history_status) {
        this.history_status = history_status;
    }
}
