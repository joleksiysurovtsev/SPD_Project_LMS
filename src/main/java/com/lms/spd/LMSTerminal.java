package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.LiteratureServiceImpl;
import com.lms.spd.services.interfaces.LectureService;
import com.lms.spd.services.interfaces.LiteratureService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class LMSTerminal {
    public static LecturesCash cash = new LecturesCash();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    LectureService lectureServiceImpl = new LectureServiceImpl();
    LiteratureService literatureServiceImpl = new LiteratureServiceImpl();
    LMSConsolePrinter print = new LMSConsolePrinter();
    LMSUtilsHelper utilsHelper = new LMSUtilsHelper();

    public void startLMS() {
        print.showStartMenu();
        try {
            switch (reader.readLine()) {
                case "1":
                    point1MainMenuShowLectures();
                    break;
                case "2":
                    point2MainMenuAddingLecture();
                    break;
                case "3":
                    point3MainMenuRemovalLecture();
                    break;
                case "4":
                    point4MainMenuChoiceOfLecture();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                    startLMS();
                    break;
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    /**
     * point 1 main menu: view a list of lectures
     */
    private void point1MainMenuShowLectures() throws IOException {
        print.printMenuPoint1();
        String choice = reader.readLine().toLowerCase();
        switch (choice) {
            case "+":
                print.printAllLectureTable(lectureServiceImpl.getLectures());
                break;
            case "-":
                System.out.println("Enter numbers separated by commas");
                print.printLectureListByNumber(reader.readLine(), lectureServiceImpl.getLectures());
                break;
            case "small":
                print.printPreviewLectureList(lectureServiceImpl.getLectures());
                break;
            case "type":
                print.printLectureListByType(utilsHelper.selectLectureType(utilsHelper.arrayLectTypesInvolved(lectureServiceImpl.getLectures())), lectureServiceImpl.getLectures());
                break;
            case "date":
                utilsHelper.cashDate(cash);
                print.printAllLectureTable(cash.returnList());
                break;
            default:
                point1MainMenuShowLectures();
                break;
        }
        System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program ");
        subMenuShowLectures();
    }

    private void subMenuShowLectures() throws IOException {
        String s = reader.readLine().toUpperCase();
        if ("0".equals(s)) {
            startLMS();
        } else if ("EXIT".equals(s)) {
            System.exit(0);
        } else {
            System.out.println("you must enter either \"0\" or EXIT");
            subMenuShowLectures();
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    private void point2MainMenuAddingLecture() throws IOException {
        String nameOfLecture = utilsHelper.createTheLectureTitle();
        String lectorName = utilsHelper.enterLektorName();
        LectureType lectureType = utilsHelper.selectLectureType();
        Calendar lectureDate = utilsHelper.enterTheLectureDate();
        List<Literature> literatures = utilsHelper.addLitOrNot();
        lectureServiceImpl.addLecture(new LectureIModel(lectureType, 1, nameOfLecture, literatures, lectorName, lectureDate));
        System.out.println("Entering a new lecture?");
        subMenuAddingLectureToList();
    }

    private void subMenuAddingLectureToList() throws IOException {
        System.out.println("what to do next: add another one? entering \"+\"" + "\u001B[32m" + " \"-\"" + "\u001B[0m"
                + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
        switch (reader.readLine().toUpperCase()) {
            case "+":
                point2MainMenuAddingLecture();
                break;
            case "-":
                startLMS();
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("you must enter either \"+\" \"-\" or EXIT");
                subMenuAddingLectureToList();
                break;
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    private void point3MainMenuRemovalLecture() throws IOException {
        System.out.println("Please enter the number of the lecture if you want to delete one or more comma separated ");
        String numbRemovalLecture = null;
        try {
            numbRemovalLecture = reader.readLine();
        } catch (IOException e) {
            point3MainMenuRemovalLecture();
        }
        assert numbRemovalLecture != null;
        String[] lectureRemove = utilsHelper.stringToDeleteLecture(numbRemovalLecture, lectureServiceImpl.getLectures());
        lectureServiceImpl.removeLectures(lectureRemove);
        subMenuRemovalLecture();

    }

    private void subMenuRemovalLecture() throws IOException {
        System.out.println("Do you want to delete another one? \n" +
                "if yes then enter " + "\u001B[31m" + "\"+\" " +
                "\u001B[0m" + "if you wont return to the menu " + "\u001B[32m" + "\"-\"" +
                "\u001B[0m" + "" + " or \u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");

        switch (reader.readLine().toUpperCase()) {
            case "+":
                point3MainMenuRemovalLecture();
                break;
            case "-":
                return;
            case "EXIT":
                System.exit(0);
                break;
            default:
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
        int numbOfLecture = 0;
        boolean flag = true;
        while (flag) {
            //проверили ввели ли номер
            try {
                numbOfLecture = Integer.parseInt(reader.readLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("You must type a lecture number!");
            }
        }
        if (numbOfLecture == 0) {
            return;
        } else {
            if (!(lectureServiceImpl.getLectures().size() >= numbOfLecture - 1)) {
                System.out.println("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again");
                point4MainMenuChoiceOfLecture();
            } else {
                lectureServiceImpl.setSelectedLecture(numbOfLecture - 1);
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
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(reader.readLine());
                break;
            } catch (NumberFormatException | IOException e) {
                System.out.println("You only need to enter a number!");
            }
        }
        switch (choice) {
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
                return;
            default:
                System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
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
        Literature newLit = utilsHelper.createLit();
        if (lectureServiceImpl.getSelectedLecture().getLiteratures().contains(newLit)) {
            System.out.println("this literature is already there");
        } else {
            literatureServiceImpl.addLiterature(newLit, lectureServiceImpl.getSelectedLecture().getLiteratures());
            System.out.println("Book added what to do next");
        }
        System.out.println("Add more ? if YES then enter \"+\" if NOT then \"-\" " +
                "you will return to the lecture selection menu, to complete the work, exit ");
        String x;
        while (true) {
            x = reader.readLine();
            if (x.equalsIgnoreCase("+") || x.equalsIgnoreCase("-") || x.equalsIgnoreCase("exit")) break;
            System.out.println("You only need to enter a + or - or EXIT!");
        }
        switch (x.toUpperCase()) {
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
                break;
        }
    }

    private void point4_4DeleteLit() throws IOException {
        System.out.println("Please enter the number of the book you want to delete");
        int indexLit = 0;
        try {
            indexLit = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
            point4_4DeleteLit();
        }
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
        switch (reader.readLine()) {
            case "+":
                point4_4DeleteLit();
                break;
            case "-":
                point4MainMenuChoiceOfLecture();
                break;
            default:
                System.out.println("please choice from the offered");
                break;
        }
    }

    private void point4_5showLectureInfo() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Lecture lecture = lectureServiceImpl.getSelectedLecture();
        StringBuilder lectureInfo = new StringBuilder("Lecture: №" + lecture.getNumberOfLecture() + " " + lecture.getNameOfLecture() + " \n");
        if (!lecture.getLectorName().isEmpty() || lecture.getLectorName() != null) {
            lectureInfo.append("The lecture is lecturing by: ").append(lecture.getLectorName()).append("\n");
        }
        if (lecture.getLectureDate() != null) {
            lectureInfo.append("Lecture date: ").append(sdf.format(lecture.getLectureDate().getTime()));
        }
        if (lecture.getType() != null) {
            lectureInfo.append(" Lecture Type: ").append(lecture.getType());
        }

        System.out.println(lectureInfo);
        print.printListLit(lecture);
        print.showFourthMenu();
        subMenu2Point4();
    }
}
