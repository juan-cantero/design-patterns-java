package io.juanqui.utils;

public class Colors {
    public static final String RESET = "\033[0m";   // Text Reset
    public static final String BLACK = "\033[30m";  // Black
    public static final String RED = "\033[31m";    // Red
    public static final String GREEN = "\033[32m";  // Green
    public static final String YELLOW = "\033[33m"; // Yellow
    public static final String BLUE = "\033[34m";   // Blue
    public static final String MAGENTA = "\033[35m"; // Magenta
    public static final String CYAN = "\033[36m";   // Cyan
    public static final String WHITE = "\033[37m";  // White

    public static void main(String[] args) {
        System.out.println(RED + "This is red text" + RESET);
        System.out.println(GREEN + "This is green text" + RESET);
        System.out.println(BLUE + "This is blue text" + RESET);
        System.out.println(YELLOW + "This is yellow text" + RESET);
    }

    public static String color(String text, String color) {
        return color + text + RESET;
    }

    public static String red(String text) {
        return color(text, RED);
    }

    public static String green(String text) {
        return color(text, GREEN);
    }

    public static String blue(String text) {
        return color(text, BLUE);
    }

    public static String cyan(String text) {
        return color(text, CYAN);
    }
}
