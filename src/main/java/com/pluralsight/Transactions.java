package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transactions {
    private LocalDate date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    private static final DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public Transactions(String date, String time, String description,String vendor, double amount){
        this.date=LocalDate.parse(date,dateFormatter );
        this.time=time;
        this.description=description;
        this.vendor=vendor;
        this.amount=amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date, dateFormatter);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
    }
}
