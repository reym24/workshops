package com.pluralsight;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;
    private final double INTEREST_RATE = 0.04;
    private final int TERM = 36;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = vehicleSold.getPrice() * 0.5;
        this.leaseFee = vehicleSold.getPrice() * 0.07;
        calculateTotalPrice();
        calculateMonthlyPayment();
    }

    @Override
    public void calculateTotalPrice() {
        double price = getVehicleSold().getPrice();
        setTotalPrice(price + leaseFee);
    }

    @Override
    public void calculateMonthlyPayment() {
        double price = getVehicleSold().getPrice();
        double monthlyRate = INTEREST_RATE / 12;
        double payment = (price * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -TERM));
        setMonthlyPayment(payment);
    }

    // Getters
    public double getExpectedEndingValue() { return expectedEndingValue; }
    public double getLeaseFee() { return leaseFee; }
}
