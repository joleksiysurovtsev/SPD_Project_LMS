package com.lms.spd;

import com.lms.spd.exceptions.DateFormatException;
import com.lms.spd.exceptions.ValidateInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ConsoleInputValidator {


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final String LECTURE_DATE_REG_EXP = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((20|2[0-9])[0-9]{2}) ([01]?[0-9]|2[0-3]):[0-5][0-9]$";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");

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
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException ioException) {
            System.err.println("Input wrong");
        }
        if (line.isEmpty()) return "Unknown";
        return line;
    }

    public static Calendar enterTheDate() {
        System.out.println("Enter the lecture date for example: 19-10-2020 18:30");
        Calendar lectureDate = new GregorianCalendar();
        lectureDate.setTimeZone(TimeZone.getDefault());
        String dateInString;
        while (true) {
            dateInString = ConsoleInputValidator.readString();
            try {
                DATE_FORMAT.setLenient(false);
                lectureDate.setTime(DATE_FORMAT.parse(dateInString));
                if (!dateInString.matches(LECTURE_DATE_REG_EXP)) {
                    throw new DateFormatException("the date of the lecture cannot be later than 2000");
                }
                break;
            } catch (DateFormatException f) {
                System.err.println(f.getMessage());
            } catch (ParseException e) {
                System.err.println("The date is entered incorrectly, try again");
            }
        }
        return lectureDate;
    }

    public static Calendar enterTheDateOnlyDayMonthYear() {
        System.out.println("Enter the lecture date for example: 19-10-2020");
        Calendar lectureDate = new GregorianCalendar();
        lectureDate.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat dateFormatOnlyDayOfWeekMonthYear = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString;
        while (true) {
            dateInString = ConsoleInputValidator.readString();
            try {
                lectureDate.setTime(dateFormatOnlyDayOfWeekMonthYear.parse(dateInString));
                break;
            } catch (ParseException e) {
                System.err.println("The date is entered incorrectly, try again");
            }
        }
        return lectureDate;
    }

    public static String readString(String validate) {
        if (validate.equals("lectureName")) {
            System.out.println("Enter the title of the lecture");
        }
        String line = null;
        while (true) {
            try {
                line = reader.readLine();
                if (validate.equals("lectureName") && line.isEmpty()) {
                    throw new ValidateInputException("The lecture must have a title");
                } else break;
            } catch (IOException | ValidateInputException exception) {
                System.out.println(exception.getMessage());
            }
        }
        assert line != null;
        if (line.isEmpty()) return "Unknown";
        return line;
    }
}