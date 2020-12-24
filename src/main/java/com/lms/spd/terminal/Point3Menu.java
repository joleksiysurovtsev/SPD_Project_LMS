package com.lms.spd.terminal;

import com.lms.spd.*;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;

public class Point3Menu implements ITerminal {

    private LectureService lectureServiceImpl = new LectureServiceImpl();
    private LMSConsolePrinter print = new LMSConsolePrinter();


    @Override
    public void showContext() {
        System.out.println("Please enter the ID of the lecture if you want to delete one or more comma separated ");
        int[] arr = Util.getStringsNumberLecture(ConsoleInputValidator.readString());
        if (arr == null) subMenuRemovalLecture();
        else {
            lectureServiceImpl.removeLectures(arr);
        }
        subMenuRemovalLecture();
    }

    private void subMenuRemovalLecture() {
        System.out.println("Do you want to delete another one? \n" +
                "if yes then enter " + "\u001B[31m" + "\"+\" " +
                "\u001B[0m" + "if you wont return to the menu " + "\u001B[32m" + "\"-\"" +
                "\u001B[0m" + "" + " or \u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");

        switch (ConsoleInputValidator.readString().toUpperCase()) {
            case "+":
                showContext();
                break;
            case "-":
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                print.printErrMassage(1);
                System.out.println("please make the right choice \"+\" \"-\" or \"EXIT\"");
                subMenuRemovalLecture();
                break;
        }
    }
}
