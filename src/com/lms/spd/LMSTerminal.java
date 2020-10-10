package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LMSTerminal {
    static ListOfLectures ls = new ListOfLectures();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void startLMS() throws IOException {
        System.out.println("\u001B[34m" + "Welcome to the " + "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" + "\"" + ": learning management system\n" + "\u001B[0m");
        showMainMenu();

        switch (reader.readLine()) {
            case "1":
                ls.getLectureList();
                System.out.println("The list brought out what to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                lectureOutputMenu();
                break;
            case "2":
                System.out.println("Please enter name for new lecture");
                boolean flag;
                do {
                    flag = false;
                    String name = reader.readLine();
                    ls.addLecture(name);
                    System.out.println("You have entered a new lecture if you want to add more " +
                            "enter " + "\u001B[32m" + "\"+\"" + "\u001B[0m" + " \nor " + "\u001B[31m" + "\"-\"" + "\u001B[0m" + " to return to the main menu   ");
                    String addOrNot = reader.readLine();
                    if (addOrNot.equalsIgnoreCase("+")) {
                        flag = true;
                        System.out.println("add more");
                    } else {
                        if (addOrNot.equalsIgnoreCase("-")) {
                            startLMS();
                        } else {
                            if (addOrNot.equalsIgnoreCase("EXIT")) {
                                System.exit(0);
                            }
                        }
                    }
                } while (flag);
                break;

            case "3":
                System.out.println("Please enter the number of the lecture you want to delete");
                boolean flagDelete;
                do {
                    flagDelete = false;
                    int number = Integer.parseInt(reader.readLine());
                    ls.removeLecture(number);
                    System.out.println("Lecture deleted want to delete another one? " +
                            "if yes then enter " + "\u001B[31m" + "\"+\" " + "\u001B[0m" +
                            "if you return to the menu " + "\u001B[32m" + "\"-\"" + "\u001B[0m" + "" + " or \u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                    String deleteOrNot = reader.readLine();
                    if (deleteOrNot.equalsIgnoreCase("+")) {
                        flagDelete = true;
                        System.out.println("Delete another enter the number");
                    } else {
                        if (deleteOrNot.equalsIgnoreCase("-")) {
                            startLMS();
                        } else {
                            if (deleteOrNot.equalsIgnoreCase("EXIT")) {
                                System.exit(0);
                            }
                        }
                    }
                } while (flagDelete);
                break;
            case "4":
                chooseLecture();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("\u001B[35m" + "No such menu item exists try again" + "\u001B[0m");
                workingFourthMenu();
        }

    }

    /**
     * Method of calling the terminal main menu
     */
    private static void showMainMenu() {
        System.out.println("Please make your choice from the offered options\n"
                + "1. Display all available lectures (number and title)\n"
                + "2. Add a new lecture\n"
                + "3. Delete a lecture by its number\n"
                + "4. Choose a lecture\n"
                + "0. \u001B[31mExit.\n\u001B[0m");
    }

    /**
     * method for navigation: return to main navigation menu
     */
    private static void lectureOutputMenu() throws IOException {
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
     * lecture selection and lecture submenu
     */
    static void chooseLecture() throws IOException {
        System.out.println("Enter the number of the lecture, " +
                "information about which you want to see if you change your mind to exit to the menu enter " + "\u001B[32m" + "0" + "\u001B[0m");
        int numbOfLecture = Integer.parseInt(reader.readLine());
        //устанавливаем номер

        if (numbOfLecture == 0) {
            startLMS();
        }

        if (numbOfLecture > ls.getArrLectureLength()) {
            System.out.println("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again");
            chooseLecture();
        } else {
            ls.setSelectedLecture(numbOfLecture - 1);
            System.out.println("Selected lecture " + "\u001B[35m" + "\"" + ls.getNameSelectedLecture() + "\"" + "\u001B[0m");
            System.out.println("What are the next actions?");

            showFourthMenu();
            workingFourthMenu();
        }
    }

    static void workingFourthMenu() throws IOException {
        switch (reader.readLine()) {
            case "1":
                ls.getListLit(ls.getSelectedLecture());
                System.out.println("_______________________");
                System.out.println("what do we do with the bibliography");
                showFourthMenu();
                workingFourthMenu();
                break;
            case "2":
                System.out.println("add new literature (title / link)");
                break;
            case "3":
                System.out.println("remove");
                break;
            case "4":
                chooseLecture();
                break;

            default:
                System.out.println("\u001B[35m" + "No such menu item exists try again" + "\u001B[0m");
                workingFourthMenu();
                break;
        }
    }

    static void showFourthMenu() {
        System.out.println("1. --> view the list of literature");
        System.out.println("2. --> add new (title / link)");
        System.out.println("3. --> remove literature");
        System.out.println("4. --> go to the menu one level higher");
        System.out.println("____________________");
    }
}
