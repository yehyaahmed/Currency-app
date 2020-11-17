package com.currencyapplication.currencyapplicatio.Models;

public class GoldPriceItem {

    private int id;
    private int gold_value;
    private int gold_history;
    private String gold_history_status;
    private int gold_history_def;

    public GoldPriceItem(int id, int gold_value, int gold_history, String gold_history_status, int gold_history_def) {
        this.id = id;
        this.gold_value = gold_value;
        this.gold_history = gold_history;
        this.gold_history_status = gold_history_status;
        this.gold_history_def = gold_history_def;
    }

    public GoldPriceItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGold_value() {
        return gold_value;
    }

    public void setGold_value(int gold_value) {
        this.gold_value = gold_value;
    }

    public int getGold_history() {
        return gold_history;
    }

    public void setGold_history(int gold_history) {
        this.gold_history = gold_history;
    }

    public String getGold_history_status() {
        return gold_history_status;
    }

    public void setGold_history_status(String gold_history_status) {
        this.gold_history_status = gold_history_status;
    }

    public int getGold_history_def() {
        return gold_history_def;
    }

    public void setGold_history_def(int gold_history_def) {
        this.gold_history_def = gold_history_def;
    }
}
