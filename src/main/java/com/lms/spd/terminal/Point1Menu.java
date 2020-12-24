package com.lms.spd.terminal;

import com.lms.spd.*;
import com.lms.spd.exceptions.ListIsEmptyException;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;

class Point1Menu implements ITerminal {
    public static LecturesCache cash = new LecturesCache();
    private LectureService lectureServiceImpl = new LectureServiceImpl();
    private LMSConsolePrinter print = new LMSConsolePrinter();
    private LectureValidator lectureValidator = new LectureValidator();

    @Override
    public void showContext() {
        LMSConsolePrinter.printMenuPoint1();
        String choice = ConsoleInputValidator.readString();
        try {
            switch (choice.toLowerCase()) {
                case "+":
                    print.printAllLectureTable(lectureServiceImpl.getLectures());
                    break;
                case "-":
                    System.out.println("Enter numbers separated by commas");
                    print.printLectureListByNumber(ConsoleInputValidator.readString(), lectureServiceImpl.getLectures());
                    break;
                case "small":
                    print.printPreviewLectureList(lectureServiceImpl.getLectures());
                    break;
                case "type":
                    print.printLectureListByType(lectureValidator.selectLectureType(), lectureServiceImpl.getMapSortedByType());
                    break;
                case "date":
                    cash.setCurrentDate(ConsoleInputValidator.enterTheDate());
                    print.printAllLectureTable(cash.returnList());
                    break;
                case "type and date":
                    print.printLectureListByTypeAndDate(lectureValidator.selectLectureType(), lectureServiceImpl.getMapSortedByType(), ConsoleInputValidator.enterTheDate());
                    break;
                case "exit":
                    LMSTerminal.startLMS();
                    break;
                default:
                    print.printErrMassage(1);
                    showContext();
                    break;
            }
        } catch (ListIsEmptyException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program ");
        subMenuShowLectures();
    }

    private void subMenuShowLectures() {
        String s = ConsoleInputValidator.readString().toUpperCase();
        if ("0".equals(s)) {
            showContext();
        } else if ("EXIT".equals(s)) {
            System.exit(0);
        } else {
            print.printErrMassage(1);
            System.out.println("you must enter either \"0\" or EXIT");
            subMenuShowLectures();
        }
    }
}