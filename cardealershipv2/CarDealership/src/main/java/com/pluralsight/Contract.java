package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // Getters and setters
    public String getDate() { return date; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public Vehicle getVehicleSold() { return vehicleSold; }
    public double getTotalPrice() { return totalPrice; }
    public double getMonthlyPayment() { return monthlyPayment; }

    protected void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    protected void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }

    // Abstract methods
    public abstract void calculateTotalPrice();
    public abstract void calculateMonthlyPayment();

    public static String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}