package com.lms.spd.terminal;

import com.lms.spd.*;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.LiteratureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;
import com.lms.spd.services.interfaces.LiteratureService;

import java.util.List;

public class Point4Menu implements ITerminal {

    private LectureService lectureServiceImpl = new LectureServiceImpl();
    private LiteratureService literatureServiceImpl = new LiteratureServiceImpl();
    private LMSConsolePrinter print = new LMSConsolePrinter();
    private LiteratureValidator literatureValidator = new LiteratureValidator();

    @Override

    public void showContext() {
        System.out.println("Enter the number of the lecture, " +
                "information about which you want to see " +
                "if you change your mind to exit to the menu enter " + "\u001B[32m" + "0" + "\u001B[0m");
        int numbOfLecture = ConsoleInputValidator.readInt();
        if (numbOfLecture == 0) {
            //go to main meny
        } else {
            lectureServiceImpl.setSelectedLecture(numbOfLecture);
            if (lectureServiceImpl.getLectures().stream().noneMatch(lecture -> lecture.getId() == numbOfLecture)) {
                System.out.println("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again");
                showContext();
            } else {
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

    private void subMenu2Point4() {
        switch (ConsoleInputValidator.readInt()) {
            case 1: // 1. --> choose another lecture
                showContext();
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
                LMSTerminal.startLMS();
                break;
            default:
                print.printErrMassage(1);
                subMenu2Point4();
                break;
        }
    }

    private void point4_2ViewListOfLit() {
        print.printListLit(lectureServiceImpl.getSelectedLecture().getLiteratures());
        print.showFourthMenu();
        subMenu2Point4();
    }

    private void point4_3AddLit() {
        Literature newLit = literatureValidator.createLiterature();
        System.out.println(newLit);
        if (lectureServiceImpl.getSelectedLecture().getLiteratures().contains(newLit)) {
            System.out.println("this literature is already there");
        } else {
            List<Literature> literatures = lectureServiceImpl.getSelectedLecture().getLiteratures();
            literatures.add(newLit);
            lectureServiceImpl.getSelectedLecture().setLiteratures(literatures);
            literatureServiceImpl.addLiterature(newLit, lectureServiceImpl.getSelectedLecture().getLiteratures());
            System.out.println("Book added what to do next");
        }
        point4_3Navigate();
    }

    private void point4_3Navigate() {
        System.out.println("Add more ? if YES then enter \"+\" if NOT then \"-\" " +
                "you will return to the lecture selection menu, to complete the work, exit ");
        switch (ConsoleInputValidator.readString().toUpperCase()) {
            case "+":
                point4_3AddLit();
                break;
            case "-":
                showContext();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                print.printErrMassage(1);
                point4_3Navigate();
                break;
        }
    }

    private void point4_4DeleteLit() {
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

    private void subMenuPoint4_4DeleteLit() {
        System.out.println("\"+\" YES or \"-\" NO");
        switch (ConsoleInputValidator.readString()) {
            case "+":
                point4_4DeleteLit();
                break;
            case "-":
                showContext();
                break;
            default:
                print.printErrMassage(1);
                System.out.println("please choice from the offered");
                subMenuPoint4_4DeleteLit();
                break;
        }
    }

    private void point4_5showLectureInfo() {
        print.showAllLectureInfo(lectureServiceImpl.getSelectedLecture());
        print.showFourthMenu();
        subMenu2Point4();
    }
}
