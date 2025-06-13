package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Inventory management
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return inventory.remove(vehicle);
    }

    // ✅ Remove vehicle by VIN (used in UserInterface)
    public boolean removeVehicle(int vin) {
        return inventory.removeIf(v -> v.getVin() == vin);
    }

    // Search methods
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return inventory.stream()
                .filter(v -> v.getPrice() >= min && v.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return inventory.stream()
                .filter(v -> v.getMake().equalsIgnoreCase(make) &&
                        v.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return inventory.stream()
                .filter(v -> v.getYear() >= min && v.getYear() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return inventory.stream()
                .filter(v -> v.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return inventory.stream()
                .filter(v -> v.getOdometer() >= min && v.getOdometer() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return inventory.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(inventory);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
