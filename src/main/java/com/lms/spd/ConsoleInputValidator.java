package com.lms.spd;

import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.error.DateFormatException;
import com.lms.spd.error.ValidateInputException;
import com.lms.spd.utils.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class ConsoleInputValidator {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private static final String LECTURE_DATE_REGEX_DATE = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((20|2[0-9])[0-9]{2})";
    private static final String LECTURE_DATE_REGEX_TIME = " ([01]?[0-9]|2[0-3]):[0-5][0-9]$";

    public static int readInt() {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(BUFFERED_READER.readLine());
                break;
            } catch (NumberFormatException | IOException e) {
                ConsoleMassage.MESSAGE_ERR_INCORRECT_INPUT.printMassage();
            }
        }
        return number;
    }

    public static String readString() {
        String line = "";
        try {
            line = BUFFERED_READER.readLine();
        } catch (IOException ioException) {
            ConsoleMassage.MESSAGE_ERR_INCORRECT_INPUT.printMassage();
        }
        return line.isEmpty() ? "Unknown" : line;
    }

    public static Calendar enterTheDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Util.GLOBAL_LOGGER.info("Enter the lecture date for example: 19-10-2020 18:30");
        Calendar lectureDate = new GregorianCalendar();
        lectureDate.setTimeZone(TimeZone.getDefault());
        String dateInString;
        while (true) {
            dateInString = ConsoleInputValidator.readString();
            try {
                lectureDate.setTime(dateFormat.parse(dateInString));
                if (!dateInString.matches(LECTURE_DATE_REGEX_DATE.concat(LECTURE_DATE_REGEX_TIME))) {
                    throw new DateFormatException("the date of the lecture cannot be later than 2000");
                }
                break;
            } catch (DateFormatException f) {
                Util.GLOBAL_LOGGER.info(f.getMessage());
            } catch (ParseException e) {
                ConsoleMassage.MESSAGE_ERR_INCORRECT_INPUT.printMassage();
            }
        }
        return lectureDate;
    }

    public static String readString(String validate) {
        if (validate.equals("lectureName")) {
            ConsoleMassage.MESSAGE_ENTER_TITLE_LECTURE.printMassage();
        }
        String line;
        while (true) {
            try {
                line = BUFFERED_READER.readLine();
                if (validate.equals("lectureName") && line.isEmpty()) {
                    throw new ValidateInputException("The lecture must have a title");
                } else {
                    break;
                }
            } catch (IOException | ValidateInputException exception) {
                Util.GLOBAL_LOGGER.info(exception.getMessage());
            }
        }
        assert line != null;
        return line.isEmpty() ? "Unknown" : line;
    }

    private ConsoleInputValidator() {
    }
}