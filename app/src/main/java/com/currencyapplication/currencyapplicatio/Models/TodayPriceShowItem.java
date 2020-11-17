package com.currencyapplication.currencyapplicatio.Models;

public class TodayPriceShowItem {

    String name;
    int saleValue;
    int buyValue;
    int lastValueSale;
    int lastValueBuy;
    int historyValue;
    String historyStatus;

    public TodayPriceShowItem(String name, int saleValue, int buyValue, int lastValueSale, int lastValueBuy, int historyValue, String historyStatus) {
        this.name = name;
        this.saleValue = saleValue;
        this.buyValue = buyValue;
        this.lastValueSale = lastValueSale;
        this.lastValueBuy = lastValueBuy;
        this.historyValue = historyValue;
        this.historyStatus = historyStatus;
    }

    public TodayPriceShowItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(int saleValue) {
        this.saleValue = saleValue;
    }

    public int getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(int buyValue) {
        this.buyValue = buyValue;
    }

    public String getHistoryStatus() {
        return historyStatus;
    }

    public void setHistoryStatus(String historyStatus) {
        this.historyStatus = historyStatus;
    }

    public int getHistoryValue() {
        return historyValue;
    }

    public void setHistoryValue(int historyValue) {
        this.historyValue = historyValue;
    }

    public int getLastValueSale() {
        return lastValueSale;
    }

    public void setLastValueSale(int lastValueSale) {
        this.lastValueSale = lastValueSale;
    }

    public int getLastValueBuy() {
        return lastValueBuy;
    }

    public void setLastValueBuy(int lastValueBuy) {
        this.lastValueBuy = lastValueBuy;
    }
}
