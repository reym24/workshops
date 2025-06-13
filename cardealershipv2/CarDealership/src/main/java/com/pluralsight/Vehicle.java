package com.pluralsight;

public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;  // Changed from 'type' to match error requirements
    private String color;
    private int odometer;
    private double price;

    public Vehicle(int vin, int year, String make, String model,
                   String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    // Getters
    public int getVin() { return vin; }
    public int getYear() { return year; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getVehicleType() { return vehicleType; }  // Added to fix error
    public String getColor() { return color; }
    public int getOdometer() { return odometer; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%d %s %s %s %s (VIN: %d, %d mi, $%.2f)",
                year, color, make, model, vehicleType, vin, odometer, price);
    }
}