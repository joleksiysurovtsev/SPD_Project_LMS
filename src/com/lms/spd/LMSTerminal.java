package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LMSTerminal {
    ListOfLectures ls = new ListOfLectures();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void startLMS() throws IOException {
        System.out.println("\u001B[34m" + "Main menu " + "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" + "\"" + ": learning management system" + "\u001B[0m");
        System.out.println("Please make your choice from the offered options\n"
                + "1. Display all available lectures (number and title)\n"
                + "2. Add a new lecture\n"
                + "3. Delete a lecture by its number\n"
                + "4. Choose a lecture\n"
                + "0. \u001B[31mExit.\n\u001B[0m");

        switch (reader.readLine()) {
            case "1":
                ls.getLectureList();
                System.out.println("The list brought out what to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                lectureOutputMenu();
                break;
            case "2":
                addingLectureList();
                break;
            case "3":
                removalLectureList();
                break;
            case "4":
                chooseLecture();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                startLMS();
                break;
        }
    }

    /**
     * method for navigation: return to main navigation menu
     */
    private void lectureOutputMenu() throws IOException {
        switch (reader.readLine().toUpperCase()) {
            case "0":
                startLMS();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("you must enter either \"0\" or EXIT");
                lectureOutputMenu();
                break;
        }
    }

    /**
     * adding new lectures
     */
    private void addingLectureList() throws IOException {
        System.out.println("Please enter name for new lecture");
        String name = reader.readLine();
        ls.addLecture(name);
        subMenuAddingLectureList();
    }

    private void subMenuAddingLectureList() throws IOException {
        System.out.println("You have entered a new lecture if you want to add more " +
                "enter " + "\u001B[32m" + "\"+\"" + "\u001B[0m" + " \nor "
                + "\u001B[31m" + "\"-\"" + "\u001B[0m" + " to return to the main menu or \"EXIT\"  ");
        switch (reader.readLine().toUpperCase()) {
            case "+":
                System.out.println("add more");
                addingLectureList();
                subMenuAddingLectureList();
                break;
            case "-":
                startLMS();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("please make the right choice \"+\" \"-\" or \"EXIT\"");
                subMenuAddingLectureList();
                break;
        }
    }

    /***/
    private void removalLectureList() throws IOException {
        System.out.println("Please enter the number of the lecture you want to delete");
        int numbLectureToDel = Integer.parseInt(reader.readLine());
        if (numbLectureToDel > ls.getArrLectureLength()) {
            System.out.println("Lectures under this name do not exist");
            System.out.println("total in the database: " + ls.getArrLectureLength() + " lectures try again");
            removalLectureList();
        } else {
            ls.removeLecture(numbLectureToDel);
            System.out.println("Lecture deleted, do you want to delete another one? \n" +
                    "if yes then enter " + "\u001B[31m" + "\"+\" " +
                    "\u001B[0m" + "if you wont return to the menu " + "\u001B[32m" + "\"-\"" +
                    "\u001B[0m" + "" + " or \u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
            switch (reader.readLine().toUpperCase()) {
                case "+":
                    removalLectureList();
                    break;
                case "-":
                    startLMS();
                    break;
                case "EXIT":
                    System.exit(0);
                    break;
                default:
                    System.out.println("please make the right choice \"+\" \"-\" or \"EXIT\"");
                    subMenuAddingLectureList();
                    break;
            }
        }
    }

    private void chooseLecture() throws IOException {
        subFourthMenu();
    }

    private void subFourthMenu() throws IOException {
        System.out.println("Enter the number of the lecture, " +
                "information about which you want to see " +
                "if you change your mind to exit to the menu enter " + "\u001B[32m" + "0" + "\u001B[0m");

        int numbOfLecture = 0;
        boolean flag = true;
        while (flag) {
            try {
                numbOfLecture = Integer.parseInt(reader.readLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("You only need to enter a number!");
            }
        }

        if (numbOfLecture == 0) {
            startLMS();
        } else {
            if (numbOfLecture > ls.getArrLectureLength()) {
                System.out.println("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again");
                chooseLecture();
            } else {
                ls.setSelectedLecture(numbOfLecture - 1);
                System.out.println("Selected lecture " + "\u001B[35m" + "\"" + ls.getNameSelectedLecture() + "\"" + "\u001B[0m");
                System.out.println("What are the next actions?");
                System.out.println("__________________________");
            }
        }
        showFourthMenu();
        subFourthMenu2();
    }

    private void showFourthMenu() {
        System.out.println("1. --> choose another lecture");
        System.out.println("2. --> view the list of literature");
        System.out.println("3. --> add new literature");
        System.out.println("4. --> remove literature");
        System.out.println("5. --> exit to the main menu");
    }

    private void subFourthMenu2() throws IOException {

        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                choice = Integer.parseInt(reader.readLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("You only need to enter a number!");

            }
        }
        switch (choice) {
            case 1: // 1. --> choose another lecture
                subFourthMenu();
                break;
            case 2://  2. --> view the list of literature
                ls.getListLit(ls.getSelectedLecture());
                System.out.println("_______________________");
                System.out.println("what do we do with the bibliography");
                showFourthMenu();
                subFourthMenu2();
                break;
            case 3://  3. --> add new literature
                addLit();
                subFourthMenu2();
                break;
            case 4://  4. --> remove literature
                deleteLit();
                break;
            case 5:
                startLMS();
                break;
            default:
                System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                subFourthMenu2();
                break;
        }
    }

    private void addLit() throws IOException {

        System.out.println("Please enter the title of the new book");
        ls.addNewLiterature(reader.readLine());
        System.out.println("Book added what to do next");
        System.out.println("Add more ? if YES then enter \"+\" if NOT then \"-\" " +
                "you will return to the lecture selection menu, to complete the work, exit ");
        String x = reader.readLine();

        while (true) {
            if (x.equalsIgnoreCase("+")) break;
            if (x.equalsIgnoreCase("-")) break;
            if (x.equalsIgnoreCase("exit")) break;
            System.out.println("You only need to enter a + or - or EXIT!");
            x = reader.readLine();
        }

        switch (x.toUpperCase()) {
            case "+":
                addLit();
                break;
            case "-":
                subFourthMenu();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                break;
        }
    }

    private void deleteLit() throws IOException {
        System.out.println("Please enter the number of the book you want to delete");
        ls.removeLiterature(Integer.parseInt(reader.readLine()));
        subFourthMenu();
    }

}
