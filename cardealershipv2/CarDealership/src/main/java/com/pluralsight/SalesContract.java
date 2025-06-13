package com.pluralsight;

public class SalesContract extends Contract {
    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.salesTax = 0.05;
        this.recordingFee = 100;
        this.processingFee = (vehicleSold.getPrice() < 10000) ? 295 : 495;
        this.isFinanced = isFinanced;
        calculateTotalPrice();
        calculateMonthlyPayment();
    }

    @Override
    public void calculateTotalPrice() {
        double price = getVehicleSold().getPrice();
        double tax = price * salesTax;
        setTotalPrice(price + tax + recordingFee + processingFee);
    }

    @Override
    public void calculateMonthlyPayment() {
        if (!isFinanced) {
            setMonthlyPayment(0);
            return;
        }

        double price = getVehicleSold().getPrice();
        double rate = (price >= 10000) ? 0.0425 : 0.0525;
        int term = (price >= 10000) ? 48 : 24;

        double monthlyRate = rate / 12;
        double payment = (price * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -term));
        setMonthlyPayment(payment);
    }

    // Getters
    public double getSalesTax() { return salesTax; }
    public double getRecordingFee() { return recordingFee; }
    public double getProcessingFee() { return processingFee; }
    public boolean isFinanced() { return isFinanced; }
}