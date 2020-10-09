package com.lms.spd;

public class LMSTerminal {

    public static void startLMS() {
        System.out.println("\u001B[34m" + "Welcome to the "+"\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m"+"\""+": learning management system\n" + "\u001B[0m");
        getMainMenu();
    }

    private static void getMainMenu() {
        System.out.println("Please make your choice from the offered options\n"
                + "1. display all available lectures (number and title)\n"
                + "2. add a new lecture\n"
                + "3. delete a lecture by its number\n"
                + "4. choose a lecture\n"
                + "0. \u001B[31mExit.\n\u001B[0m");
    }
}
