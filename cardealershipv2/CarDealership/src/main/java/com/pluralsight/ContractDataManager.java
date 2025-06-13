package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContractDataManager {
    private static final String CONTRACTS_FILE = "contracts.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    public void saveContract(Contract contract) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONTRACTS_FILE, true))) {
            Vehicle vehicle = contract.getVehicleSold();
            String date = LocalDate.now().format(DATE_FORMAT);

            if (contract instanceof SalesContract sales) {
                // Calculate all sales-related values
                double salesTax = vehicle.getPrice() * 0.05; // 5% sales tax
                double recordingFee = 100.00;
                double processingFee = vehicle.getPrice() < 10000 ? 295.00 : 495.00;
                double totalPrice = vehicle.getPrice() + salesTax + recordingFee + processingFee;
                double monthlyPayment = sales.isFinanced() ?
                        calculateMonthlyPayment(vehicle.getPrice(), sales.isFinanced()) : 0.00;

                writer.println(String.join("|",
                        "SALE",
                        date,
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        String.valueOf(vehicle.getVin()),
                        String.valueOf(vehicle.getYear()),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        String.valueOf(vehicle.getOdometer()),
                        String.format("%.2f", vehicle.getPrice()),
                        String.format("%.2f", salesTax),
                        String.format("%.2f", recordingFee),
                        String.format("%.2f", processingFee),
                        String.format("%.2f", totalPrice),
                        sales.isFinanced() ? "YES" : "NO",
                        String.format("%.2f", monthlyPayment)
                ));
            } else if (contract instanceof LeaseContract lease) {
                // Calculate lease-related values
                double expectedValue = vehicle.getPrice() * 0.5;
                double leaseFee = vehicle.getPrice() * 0.07;
                double totalPrice = vehicle.getPrice() + leaseFee;
                double monthlyPayment = calculateLeasePayment(vehicle.getPrice(), expectedValue);

                writer.println(String.join("|",
                        "LEASE",
                        date,
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        String.valueOf(vehicle.getVin()),
                        String.valueOf(vehicle.getYear()),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        String.valueOf(vehicle.getOdometer()),
                        String.format("%.2f", vehicle.getPrice()),
                        String.format("%.2f", expectedValue),
                        String.format("%.2f", leaseFee),
                        String.format("%.2f", totalPrice),
                        String.format("%.2f", monthlyPayment)
                ));
            }
        } catch (IOException e) {
            System.err.println("Error saving contract: " + e.getMessage());
        }
    }

    private double calculateMonthlyPayment(double price, boolean financed) {
        if (!financed) return 0.0;
        double rate = price >= 10000 ? 0.0425 : 0.0525; // 4.25% or 5.25%
        int term = price >= 10000 ? 48 : 24; // months
        double monthlyRate = rate / 12;
        return (price * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -term));
    }

    private double calculateLeasePayment(double price, double expectedValue) {
        double leaseAmount = price - expectedValue;
        double rate = 0.04; // 4% APR
        int term = 36; // months
        double monthlyRate = rate / 12;
        return (leaseAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -term));
    }
}