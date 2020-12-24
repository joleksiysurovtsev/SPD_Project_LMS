package com.lms.spd.terminal;

import com.lms.spd.*;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;

class Point2Menu implements ITerminal {

    private LectureService lectureServiceImpl = new LectureServiceImpl();
    private LMSConsolePrinter print = new LMSConsolePrinter();
    private LectureValidator lectureValidator = new LectureValidator();

    @Override
    public void showContext() {
        lectureServiceImpl.addLecture(lectureValidator.createLecture());
        System.out.println("Entering a new lecture?");
        subMenuAddingLectureToList();
    }

    private void subMenuAddingLectureToList() {
        System.out.println("what to do next: add another one? entering \"+\"" + "\u001B[32m" + " \"-\"" + "\u001B[0m"
                + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
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
                System.out.println("you must enter either \"+\" \"-\" or EXIT");
                subMenuAddingLectureToList();
                break;
        }
    }
}

