package com.lms.spd.terminalmenuitems;

import com.lms.spd.LMSConsolePrinter;
import com.lms.spd.LMSTerminal;
import com.lms.spd.LMSUtilsHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class LMSTerminalPoint1 {
    public LMSTerminalPoint1() throws IOException {
        point1MainMenuShowLectures();
    }

    LMSTerminal terminal = new LMSTerminal();
    LMSConsolePrinter print = new LMSConsolePrinter();
    LMSUtilsHelper utilsHelper = new LMSUtilsHelper();

    /**
     * point 1 main menu: view a list of lectures
     */
    private void point1MainMenuShowLectures() throws IOException {
        print.printMenuPoint1();
        String choice = LMSTerminal.reader.readLine().toLowerCase();
        switch (choice) {
            case "+":
                print.printAllLectureTable(LMSTerminal.lectureServiceImpl.getLectures());
                break;
            case "-":
                System.out.println("Enter numbers separated by commas");
                print.printLectureListByNumber(LMSTerminal.reader.readLine(), LMSTerminal.lectureServiceImpl.getLectures());
                break;
            case "small":
                print.printPreviewLectureList(LMSTerminal.lectureServiceImpl.getLectures());
                break;
            case "type":
                print.printLectureListByType(utilsHelper.selectLectureType(utilsHelper.arrayLectTypesInvolved(LMSTerminal.lectureServiceImpl.getLectures())), LMSTerminal.lectureServiceImpl.getLectures());
                break;
            case "date":
                changeDate();
                print.printAllLectureTable(LMSTerminal.cash.returnList());
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
        System.out.print("Displayed lectures for :" + sdf.format(LMSTerminal.cash.getCurentDate().getTime()) + " if you want to change the date enter otherwise press enter ");
        if (!LMSTerminal.reader.readLine().isEmpty()) {
            LMSTerminal.cash.setCurentDate(utilsHelper.enterTheLectureDate());
        }
        System.out.println("Date " + sdf.format(LMSTerminal.cash.getCurentDate().getTime()));
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

}
