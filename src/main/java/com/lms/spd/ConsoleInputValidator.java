package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputValidator {


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static int readInt() {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(reader.readLine());
                break;
            } catch (NumberFormatException | IOException e) {
                System.err.println("Invalid format you must enter a number");
            }
        }
        return number;
    }

    public static String readString() {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        if (line.isEmpty()) return "Unknown";
        return line;
    }

}
