package com.pluralsight;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    // Display methods
    public static void display(String message) {
        System.out.print(message);
    }

    public static void displayLine(String message) {
        System.out.println(message);
    }

    public static void displayError(String message) {
        System.err.println(message);
    }

    // Input methods
    public static String getStringInput(String prompt) {
        display(prompt);
        return scanner.nextLine().trim();
    }

    public static int getIntInput(String prompt) {
        while (true) {
            try {
                display(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayError("Please enter a valid integer.");
            }
        }
    }

    public static int getIntInput(String prompt, int min, int max) {
        while (true) {
            int value = getIntInput(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            displayError(String.format("Please enter a number between %d and %d.", min, max));
        }
    }

    public static double getDoubleInput(String prompt) {
        while (true) {
            try {
                display(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayError("Please enter a valid number.");
            }
        }
    }

    public static double getDoubleInput(String prompt, double min) {
        while (true) {
            double value = getDoubleInput(prompt);
            if (value >= min) {
                return value;
            }
            displayError(String.format("Please enter a number greater than or equal to %.2f.", min));
        }
    }

    public static boolean getYesNoInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt + " (Y/N): ").toUpperCase();
            if (input.equals("Y") || input.equals("YES")) {
                return true;
            } else if (input.equals("N") || input.equals("NO")) {
                return false;
            }
            displayError("Please enter Y or N.");
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just print some newlines
            displayLine("\n\n\n\n\n");
        }
    }

    public static void pause() {
        displayLine("\nPress Enter to continue...");
        scanner.nextLine();
    }
}