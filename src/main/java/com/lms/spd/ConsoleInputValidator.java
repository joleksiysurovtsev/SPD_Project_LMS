package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public static Calendar enterTheDate() {
        System.out.println("Enter the lecture date for example: 19-10-1986");
        Calendar d1 = new GregorianCalendar();
        String dateInString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        while (true) {
            dateInString = ConsoleInputValidator.readString();
            try {
                sdf.setLenient(true);
                d1.setTime(sdf.parse(dateInString));
                break;
            } catch (ParseException e) {
                System.out.println("The date is entered incorrectly, try again");
            }
        }
        return d1;
    }

}
