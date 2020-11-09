package com.lms.spd;

import com.lms.spd.models.interfaces.Lecture;

import java.io.IOException;

public class LMSTerminalPoint4 {
    public LMSTerminalPoint4() throws IOException {
        point4MainMenuChoiceOfLecture();
    }

    private void point4MainMenuChoiceOfLecture() throws IOException {
        System.out.println("Enter the number of the lecture, " +
                "information about which you want to see " +
                "if you change your mind to exit to the menu enter " + "\u001B[32m" + "0" + "\u001B[0m");
        int numbOfLecture = 0;
        boolean flag = true;
        while (flag) {
            //проверили ввели ли номер
            try {
                numbOfLecture = Integer.parseInt(LMSTerminal.reader.readLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("You must type a lecture number!");
            }
        }
        if (numbOfLecture == 0) {
            LMSTerminal.startLMS();
        } else {
            if (!(LMSTerminal.lectureServiceImpl.getLectures().size() >= numbOfLecture - 1)) {
                System.out.println("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again");
                point4MainMenuChoiceOfLecture();
            } else {
                LMSTerminal.lectureServiceImpl.setSelectedLecture(numbOfLecture - 1);
                System.out.println("Selected lecture " + "\u001B[35m" + "\"" + LMSTerminal.lectureServiceImpl.getSelectedLecture().toString() + "\"" + "\u001B[0m");
                System.out.println("What are the next actions?");
                System.out.println("__________________________");
            }
        }
        LMSTerminal.lmsConsolePrinter.showFourthMenu();
        subMenu2Point4();
    }

    private void subMenu2Point4() throws IOException {
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                choice = Integer.parseInt(LMSTerminal.reader.readLine());
                flag = false;
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
                LMSTerminal.startLMS();
                break;
            default:
                System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                subMenu2Point4();
                break;
        }
    }


    private void point4_2ViewListOfLit() throws IOException {
        LMSTerminal.lectureServiceImpl.getSelectedLecture().printListLit(LMSTerminal.lmsConsolePrinter);
        System.out.println("what do we do with the bibliography");
        LMSTerminal.lmsConsolePrinter.showFourthMenu();
        subMenu2Point4();
    }

    private void point4_3AddLit() throws IOException {
//        Literature newLit = createLit();
//        if (lectureServiceImpl.getSelectedLecture().getLiteratures().contains(newLit)){
//            System.out.println("this literature is already there");
//        }else{
//            literatureServiceImpl.addLiterature(newLit, lectureServiceImpl.getSelectedLecture().getLiteratures());
//            System.out.println("Book added what to do next");
//        }
//        System.out.println("Add more ? if YES then enter \"+\" if NOT then \"-\" " +
//                "you will return to the lecture selection menu, to complete the work, exit ");
        String x;
        while (true) {
            x = LMSTerminal.reader.readLine();
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
            indexLit = Integer.parseInt(LMSTerminal.reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
            point4_4DeleteLit();
        }
        boolean flag = false;
        if (LMSTerminal.lectureServiceImpl.getSelectedLecture().getLiteratures().size() >= indexLit) {
            flag = true;
        }
        if (!flag) {
            System.out.println("Literature under this number do not exist");
            System.out.println("try again");
            return;
        }
        LMSTerminal.lectureServiceImpl.getSelectedLecture().setLiteratures(LMSTerminal.literatureServiceImpl.removeLiterature(indexLit, LMSTerminal.lectureServiceImpl.getSelectedLecture().getLiteratures()));
        System.out.println("\"Successfully\" Delete again ?");
        System.out.println("\"+\" YES or \"-\" NO");
        subMenuPoint4_4DeleteLit();
    }

    private void subMenuPoint4_4DeleteLit() throws IOException {
        System.out.println("\"+\" YES or \"-\" NO");
        switch (LMSTerminal.reader.readLine()) {
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
        Lecture lecture = LMSTerminal.lectureServiceImpl.getSelectedLecture();
        StringBuilder lectureInfo = new StringBuilder("Lecture: №" + lecture.getNumberOfLecture() + " " + lecture.getNameOfLecture() + " \n");
        if (!lecture.getLectorName().isEmpty() || lecture.getLectorName() != null) {
            lectureInfo.append("The lecture is lecturing by: ").append(lecture.getLectorName()).append("\n");
        }
        if (lecture.getLectureDate() != null) {
            lectureInfo.append("Lecture date: ").append(lecture.getLectureDate());
        }
        if (lecture.getType() != null) {
            lectureInfo.append(" Lecture Type: ").append(lecture.getType());
        }
        System.out.println(lectureInfo);
        LMSTerminal.lmsConsolePrinter.showFourthMenu();
        subMenu2Point4();
    }
}
