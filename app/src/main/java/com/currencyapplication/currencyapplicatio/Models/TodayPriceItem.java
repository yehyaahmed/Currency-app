package com.currencyapplication.currencyapplicatio.Models;

public class TodayPriceItem {

    int id;
    String name;

    public TodayPriceItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TodayPriceItem() {
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
}
