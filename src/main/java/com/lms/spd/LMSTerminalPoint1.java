package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.IntStream;

public class LMSTerminalPoint1 {

    public static LecturesCash cash = new LecturesCash();
    LMSTerminal terminal = new LMSTerminal();

    public LMSTerminalPoint1() throws IOException {
        point1MainMenuShowLectures();
    }

    /**
     * point 1 main menu: view a list of lectures
     */
    private void point1MainMenuShowLectures() throws IOException {
        System.out.println("\u001b[36;1m\"+\"\u001B[0m Display all lectures\n"
                + "\u001b[31;1m\"-\" \u001B[0mSpecifically some by numbers\n"
                + "\u001B[32m\"SMALL\"\u001B[0m To preview lectures\n"
                + "\u001B[35m\"TYPE\"\u001B[0m Display lectures of a certain type \n"
                + "\u001B[36m\"DATE\"\u001B[0m Display lectures by curend date ");

        String choice = LMSTerminal.reader.readLine().toLowerCase();
        switch (choice) {
            case "+":
                LMSTerminal.lmsConsolePrinter.printLectureList(LMSTerminal.lectureServiceImpl.getLectures());
                break;
            case "-":
                System.out.println("Enter numbers separated by commas");
                LMSTerminal.lmsConsolePrinter.printLectureList(LMSTerminal.reader.readLine(), LMSTerminal.lectureServiceImpl.getLectures());
                break;
            case "small":
                LMSTerminal.lmsConsolePrinter.printPreviewLectureList(LMSTerminal.lectureServiceImpl.getLectures());
                break;
            case "type":
                LMSTerminal.lmsConsolePrinter.printLectureListByType(selectLectureType(arrayLectTypesInvolved(LMSTerminal.lectureServiceImpl.getLectures())), LMSTerminal.lectureServiceImpl.getLectures());
                break;
            case "date":
                changeDate();
                LMSTerminal.lmsConsolePrinter.printLectureList(cash.returnList());
                break;
            default:
                point1MainMenuShowLectures();
                break;
        }
        System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program ");
        subMenuShowLectures();
    }

    private void changeDate() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.print("Displayed lectures for :" + sdf.format(cash.getCurentDate().getTime()) + " if you want to change the date enter otherwise press enter ");
        if (!LMSTerminal.reader.readLine().isEmpty()) {
            cash.setCurentDate(enterTheLectureDate());
        }
        System.out.println("Date " + sdf.format(cash.getCurentDate().getTime()));
    }

    private void subMenuShowLectures() throws IOException {
        String s = LMSTerminal.reader.readLine().toUpperCase();
        if ("0".equals(s)) {
            terminal.startLMS();
        } else if ("EXIT".equals(s)) {
            System.exit(0);
        } else {
            System.out.println("you must enter either \"0\" or EXIT");
            subMenuShowLectures();
        }
    }

    /**
     * Returns the lecture type implemented by type checking.
     */
    private LectureType selectLectureType() {
        System.out.println("Please, choose lecture type: ");
        IntStream.range(1, LectureType.values().length + 1).mapToObj(i -> i + ". " + LectureType.getValueByNumber(i) + " ").forEach(System.out::println);
        int number = 0;
        try {
            number = Integer.parseInt(LMSTerminal.reader.readLine());
            if (number > LectureType.values().length || number <= 0) {
                System.out.println("Unknown type: try again");
                selectLectureType();
            }
        } catch (IOException e) {
            System.out.println("Unknown type: try again");
            selectLectureType();
        }
        return LectureType.getValueByNumber(number);
    }

    private LectureType selectLectureType(List<LectureType> types) {
        int number;
        while (true) {
            System.out.println("Please, choose lecture type: ");
            IntStream.range(0, types.size()).mapToObj(i -> (i + 1) + ". " + types.get(i) + " ").forEach(System.out::println);
            try {
                number = Integer.parseInt(LMSTerminal.reader.readLine());
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

    private List<LectureType> arrayLectTypesInvolved(List<Lecture> lectures) {
        List<LectureType> types = new ArrayList<>();
        lectures.stream().filter(lecture -> !types.contains(lecture.getType())).forEach(lecture -> types.add(lecture.getType()));
        return types;
    }

    private Calendar enterTheLectureDate() throws IOException {
        System.out.println("Enter the lecture date for example: 19-10-1986");
        Calendar d1 = new GregorianCalendar();
        String dateInString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        dateInString = LMSTerminal.reader.readLine();
        try {
            sdf.setLenient(true);
            d1.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            d1 = Calendar.getInstance();
            System.out.println("The date is entered incorrectly, the date of the lecture is set two months from the current");
        }
        return d1;
    }
}
