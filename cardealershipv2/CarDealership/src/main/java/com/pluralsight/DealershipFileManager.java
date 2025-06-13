package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {
    private static final String INVENTORY_FILE = "dealership.csv";

    public Dealership getDealership() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            // Read dealership info (first line)
            String dealershipInfo = reader.readLine();
            if (dealershipInfo == null) {
                return createDefaultDealership();
            }

            String[] dealershipData = dealershipInfo.split("\\|");
            Dealership dealership = new Dealership(
                    dealershipData[0], // name
                    dealershipData[1], // address
                    dealershipData[2]  // phone
            );

            // Read vehicles
            String vehicleLine;
            while ((vehicleLine = reader.readLine()) != null) {
                String[] vehicleData = vehicleLine.split("\\|");
                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(vehicleData[0]), // vin
                        Integer.parseInt(vehicleData[1]), // year
                        vehicleData[2], // make
                        vehicleData[3], // model
                        vehicleData[4], // vehicleType
                        vehicleData[5], // color
                        Integer.parseInt(vehicleData[6]), // odometer
                        Double.parseDouble(vehicleData[7]) // price
                );
                dealership.addVehicle(vehicle);
            }
            return dealership;

        } catch (IOException e) {
            System.err.println("Error loading inventory - creating default dealership");
            return createDefaultDealership();
        }
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(INVENTORY_FILE))) {
            // Write dealership info
            writer.println(String.join("|",
                    dealership.getName(),
                    dealership.getAddress(),
                    dealership.getPhone()));

            // Write vehicles
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.println(String.join("|",
                        String.valueOf(vehicle.getVin()),
                        String.valueOf(vehicle.getYear()),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        String.valueOf(vehicle.getOdometer()),
                        String.format("%.2f", vehicle.getPrice())));
            }
        } catch (IOException e) {
            System.err.println("Error saving dealership: " + e.getMessage());
        }
    }

    private Dealership createDefaultDealership() {
        Dealership defaultDealership = new Dealership(
                "Default Dealership",
                "123 Main St",
                "555-555-5555"
        );

        // Sample inventory
        defaultDealership.addVehicle(new Vehicle(
                1001, 2022, "Toyota", "Camry", "Sedan", "Blue", 5000, 24999.99
        ));
        defaultDealership.addVehicle(new Vehicle(
                1002, 2021, "Ford", "F-150", "Truck", "Red", 15000, 34999.99
        ));

        saveDealership(defaultDealership);
        return defaultDealership;
    }
}