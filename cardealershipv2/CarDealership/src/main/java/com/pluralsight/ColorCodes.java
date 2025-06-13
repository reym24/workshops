package com.pluralsight;

public final class ColorCodes {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public static String highlightSuccess(String text) {
        return GREEN + text + RESET;
    }
}