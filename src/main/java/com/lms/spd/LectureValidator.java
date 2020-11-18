package com.lms.spd;

import com.lms.spd.enums.LectureType;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.IntStream;

public class LectureValidator {


    public LectureType selectLectureType(List<LectureType> types, LiteratureValidator literatureValidator) {
        int number;
        while (true) {
            System.out.println("Please, choose lecture type: ");
            IntStream.range(0, types.size()).mapToObj(i -> (i + 1) + ". " + types.get(i) + " ").forEach(System.out::println);
            try {
                number = Integer.parseInt(literatureValidator.reader.readLine());
                if (number > types.size() || number <= 0) {
                    System.out.println("Unknown type: try again");
                    continue;
                }
            } catch (IOException e) {
                System.out.println("Unknown type: try again");
                continue;
            }
            break;
        }
        return types.get(number - 1);
    }

    public Calendar enterTheLectureDate(LiteratureValidator literatureValidator) throws IOException {
        System.out.println("Enter the lecture date for example: 19-10-1986");
        Calendar d1 = new GregorianCalendar();
        String dateInString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        dateInString = literatureValidator.reader.readLine();
        try {
            sdf.setLenient(true);
            d1.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            System.out.println("The date is entered incorrectly, try again");
            enterTheLectureDate(literatureValidator);
        }
        return d1;
    }

    /**
     * Returns the title of the lecture after checking that it is not empty.
     *
     */
    public String createTheLectureTitle(LiteratureValidator literatureValidator) throws IOException {
        String lectureName;
        System.out.println("Enter the title of the lecture");
        while (true) {
            lectureName = literatureValidator.reader.readLine();
            if (!lectureName.isEmpty()) {
                break;
            } else {
                System.out.println("The lecture must have a title. Try again");
            }
        }
        return lectureName;
    }

    /**
     * Returns a string with the name of the lecturer, if no name is entered then the name is unknown
     * returns the title of the lecture after checking that it is not empty.
     */
    String enterLektorName(LiteratureValidator literatureValidator) throws IOException {
        System.out.println("Enter lecturer name");
        String s = literatureValidator.reader.readLine();
        return (s.isEmpty()) ? "Unknown" : s;
    }

    /**
     * Returns the lecture type implemented by type checking.
     */
    LectureType selectLectureType(LiteratureValidator literatureValidator) {
        int number = 0;
        do {
            System.out.println("Please, choose lecture type: ");
            IntStream.range(1, LectureType.values().length + 1).mapToObj(i -> i + ". " + LectureType.getValueByNumber(i) + " ").forEach(System.out::println);
            try {
                number = Integer.parseInt(literatureValidator.reader.readLine());
                if (number > LectureType.values().length || number <= 0) {
                    System.out.println("Unknown type: try again");
                    selectLectureType(literatureValidator);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Unknown type: try again");
                selectLectureType(literatureValidator);
            }
        } while (number < 0);
        return LectureType.getValueByNumber(number);
    }
}
