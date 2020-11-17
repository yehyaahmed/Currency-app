package com.currencyapplication.currencyapplicatio.Models;

public class PhoneItem {

    private String phoneName;
    private String phoneScreen;
    private String phoneCamera;
    private String phoneBattart;
    private String phoneRating;
    private String phoneSalary;

    public PhoneItem() {
    }

    public PhoneItem(String phoneName, String phoneScreen, String phoneCamera, String phoneBattart, String phoneRating, String phoneSalary) {
        this.phoneName = phoneName;
        this.phoneScreen = phoneScreen;
        this.phoneCamera = phoneCamera;
        this.phoneBattart = phoneBattart;
        this.phoneRating = phoneRating;
        this.phoneSalary = phoneSalary;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneScreen() {
        return phoneScreen;
    }

    public void setPhoneScreen(String phoneScreen) {
        this.phoneScreen = phoneScreen;
    }

    public String getPhoneCamera() {
        return phoneCamera;
    }

    public void setPhoneCamera(String phoneCamera) {
        this.phoneCamera = phoneCamera;
    }

    public String getPhoneBattart() {
        return phoneBattart;
    }

    public void setPhoneBattart(String phoneBattart) {
        this.phoneBattart = phoneBattart;
    }

    public String getPhoneRating() {
        return phoneRating;
    }

    public void setPhoneRating(String phoneRating) {
        this.phoneRating = phoneRating;
    }

    public String getPhoneSalary() {
        return phoneSalary;
    }

    public void setPhoneSalary(String phoneSalary) {
        this.phoneSalary = phoneSalary;
    }
}
