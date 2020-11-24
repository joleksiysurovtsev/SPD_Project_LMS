package com.lms.spd;

import com.lms.spd.exceptions.ListIsEmptyException;
import com.lms.spd.exceptions.NullLectureException;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.LiteratureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;
import com.lms.spd.services.interfaces.LiteratureService;

import java.io.IOException;

public class LMSTerminal {
    public static LecturesCache cash = new LecturesCache();
    private LectureService lectureServiceImpl = new LectureServiceImpl();
    private LiteratureService literatureServiceImpl = new LiteratureServiceImpl();
    private LMSConsolePrinter print = new LMSConsolePrinter();
    private LiteratureValidator literatureValidator = new LiteratureValidator();
    private LectureValidator lectureValidator = new LectureValidator();

    public void startLMS() {
        print.showStartMenu();
        try {
            switch (ConsoleInputValidator.readInt()) {
                case 1:
                    point1MainMenuShowLectures();
                    break;
                case 2:
                    point2MainMenuAddingLecture();
                    break;
                case 3:
                    point3MainMenuRemovalLecture();
                    break;
                case 4:
                    point4MainMenuChoiceOfLecture();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    print.printErrMassage(1);
                    startLMS();
                    break;
            }
            print.showStartMenu();
        } catch (ListIsEmptyException | IOException e) {
            System.err.println(e.getMessage());
            startLMS();
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    /**
     * point 1 main menu: view a list of lectures
     */
    private void point1MainMenuShowLectures() throws ListIsEmptyException {
        print.printMenuPoint1();
        String choice = ConsoleInputValidator.readString();
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
                print.printLectureListByType(lectureValidator.selectLectureType(lectureValidator.arrayLecturesTypesInvolved(lectureServiceImpl.getLectures())), lectureServiceImpl.getLectures());
                break;
            case "date":
                cash.setCurentDate(ConsoleInputValidator.enterTheDate());
                print.printAllLectureTable(cash.returnList());
                break;
            case "exit":
                startLMS();
            default:
                print.printErrMassage(1);
                point1MainMenuShowLectures();
                break;
        }
        System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program ");
        subMenuShowLectures();
    }

    private void subMenuShowLectures() {
        String s = ConsoleInputValidator.readString().toUpperCase();
        if ("0".equals(s)) {
            startLMS();
        } else if ("EXIT".equals(s)) {
            System.exit(0);
        } else {
            print.printErrMassage(1);
            System.out.println("you must enter either \"0\" or EXIT");
            subMenuShowLectures();
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    private void point2MainMenuAddingLecture() throws IOException {
        lectureServiceImpl.addLecture(lectureValidator.createLecture());
        System.out.println("Entering a new lecture?");
        subMenuAddingLectureToList();
    }

    private void subMenuAddingLectureToList() throws IOException {
        System.out.println("what to do next: add another one? entering \"+\"" + "\u001B[32m" + " \"-\"" + "\u001B[0m"
                + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
        switch (ConsoleInputValidator.readString().toUpperCase()) {
            case "+":
                point2MainMenuAddingLecture();
                break;
            case "-":
                startLMS();
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
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    private void point3MainMenuRemovalLecture() throws IOException {
        System.out.println("Please enter the number of the lecture if you want to delete one or more comma separated ");
        String numbRemovalLecture = ConsoleInputValidator.readString();
        String[] lectureRemove = lectureValidator.stringToDeleteLecture(numbRemovalLecture, lectureServiceImpl.getLectures());
        lectureServiceImpl.removeLectures(lectureRemove);
        subMenuRemovalLecture();
    }

    private void subMenuRemovalLecture() throws IOException {
        System.out.println("Do you want to delete another one? \n" +
                "if yes then enter " + "\u001B[31m" + "\"+\" " +
                "\u001B[0m" + "if you wont return to the menu " + "\u001B[32m" + "\"-\"" +
                "\u001B[0m" + "" + " or \u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");

        switch (ConsoleInputValidator.readString().toUpperCase()) {
            case "+":
                point3MainMenuRemovalLecture();
                break;
            case "-":
                startLMS();
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

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    private void point4MainMenuChoiceOfLecture() throws IOException {
        System.out.println("Enter the number of the lecture, " +
                "information about which you want to see " +
                "if you change your mind to exit to the menu enter " + "\u001B[32m" + "0" + "\u001B[0m");
        int numbOfLecture = ConsoleInputValidator.readInt();
        if (numbOfLecture == 0) {
            startLMS();
        } else {
            if ((lectureServiceImpl.getLectures().size() <= numbOfLecture - 1)) {
                System.out.println("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again");
                point4MainMenuChoiceOfLecture();
            } else {
                try {
                    lectureServiceImpl.setSelectedLecture(numbOfLecture - 1);
                } catch (NullLectureException e) {
                    System.err.println("List lecture is empty");
                }
                System.out.println("Selected lecture: ");
                System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
                print.printLectureTable(lectureServiceImpl.getSelectedLecture());
                System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
                System.out.println("What are the next actions?");
            }
        }
        print.showFourthMenu();
        subMenu2Point4();
    }

    private void subMenu2Point4() throws IOException {
        switch (ConsoleInputValidator.readInt()) {
            case 1: // 1. --> choose another lecture
                point4MainMenuChoiceOfLecture();
                break;
            case 2://  2. --> view the list of literature
                point4_2ViewListOfLit();
                break;
            case 3://  3. --> add new literature
                point4_3AddLit();
                subMenu2Point4();
                break;
            case 4://  4. --> remove literature
                point4_4DeleteLit();
                break;
            case 5://  4. --> show lecture info
                point4_5showLectureInfo();
                break;
            case 6:
                startLMS();
                break;
            default:
                print.printErrMassage(1);
                subMenu2Point4();
                break;
        }
    }

    private void point4_2ViewListOfLit() throws IOException {
       print.printListLit(lectureServiceImpl.getSelectedLecture());
       System.out.println("what do we do with the bibliography");
        print.showFourthMenu();
        subMenu2Point4();
    }

    private void point4_3AddLit() throws IOException {
        Literature newLit = literatureValidator.createLit();
        if (lectureServiceImpl.getSelectedLecture().getLiteratures().contains(newLit)) {
            System.out.println("this literature is already there");
        } else {
            literatureServiceImpl.addLiterature(newLit, lectureServiceImpl.getSelectedLecture().getLiteratures());
            System.out.println("Book added what to do next");
        }
        System.out.println("Add more ? if YES then enter \"+\" if NOT then \"-\" " +
                "you will return to the lecture selection menu, to complete the work, exit ");
        while (true) {
            switch (ConsoleInputValidator.readString().toUpperCase()) {
                case "+":
                    point4_3AddLit();
                    break;
                case "-":
                    point4MainMenuChoiceOfLecture();
                    break;
                case "EXIT":
                    System.exit(0);
                    break;
                default:
                    print.printErrMassage(1);
                    break;
            }
            break;
        }
    }

    private void point4_4DeleteLit() throws IOException {
        System.out.println("Please enter the number of the book you want to delete");
        int indexLit = ConsoleInputValidator.readInt();
        boolean flag = false;
        if (lectureServiceImpl.getSelectedLecture().getLiteratures().size() >= indexLit) {
            flag = true;
        }
        if (!flag) {
            System.out.println("Literature under this number do not exist");
            System.out.println("try again");
            return;
        }
        lectureServiceImpl.getSelectedLecture().setLiteratures(literatureServiceImpl.removeLiterature(indexLit, lectureServiceImpl.getSelectedLecture().getLiteratures()));
        System.out.println("\"Successfully\" Delete again ?");
        System.out.println("\"+\" YES or \"-\" NO");
        subMenuPoint4_4DeleteLit();
    }

    private void subMenuPoint4_4DeleteLit() throws IOException {
        System.out.println("\"+\" YES or \"-\" NO");
        switch (ConsoleInputValidator.readString()) {
            case "+":
                point4_4DeleteLit();
                break;
            case "-":
                point4MainMenuChoiceOfLecture();
                break;
            default:
                print.printErrMassage(1);
                System.out.println("please choice from the offered");
                break;
        }
    }

    private void point4_5showLectureInfo() throws IOException {
        print.showAllLectureInfo(lectureServiceImpl.getSelectedLecture());
        print.showFourthMenu();
        subMenu2Point4();
    }
}
