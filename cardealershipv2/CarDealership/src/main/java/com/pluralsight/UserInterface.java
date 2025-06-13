package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Dealership dealership;
    private final ContractDataManager contractDataManager;
    private final Scanner scanner;

    public UserInterface(Dealership dealership, ContractDataManager contractDataManager) {
        this.dealership = dealership;
        this.contractDataManager = contractDataManager;
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            System.out.println("\n=== Welcome to " + dealership.getName() + " ===");
            System.out.println("1. View All Vehicles");
            System.out.println("2. Remove Vehicle");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear bad input
                continue;
            }

            switch (choice) {
                case 1 -> displayAllVehicles();
                case 2 -> removeVehicle();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void displayAllVehicles() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in inventory.");
        } else {
            System.out.println("\n--- Vehicle Inventory ---");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    private void removeVehicle() {
        System.out.print("\nEnter VIN to remove: ");
        int vin;
        try {
            vin = scanner.nextInt();
            scanner.nextLine(); // consume newline
        } catch (Exception e) {
            System.out.println("Invalid VIN.");
            scanner.nextLine(); // clear bad input
            return;
        }

        if (dealership.removeVehicle(vin)) {
            System.out.println("Vehicle removed successfully.");
            new DealershipFileManager().saveDealership(dealership); // Save updated inventory
        } else {
            System.out.println("Vehicle not found.");
        }
    }
}
