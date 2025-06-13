package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        // Create required objects
        Dealership dealership = new Dealership("My Dealership", "123 Main St", "555-1234");
        ContractDataManager contractDataManager = new ContractDataManager();

        // Pass them into UserInterface
        UserInterface ui = new UserInterface(dealership, contractDataManager);
        ui.display();
    }
}
