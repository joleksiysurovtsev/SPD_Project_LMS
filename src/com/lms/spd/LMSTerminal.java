package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LMSTerminal {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ListOfLectures listOfLectures = new ListOfLectures();

    public void startLMS() {
        System.out.println("\u001B[34m" + "Main menu " + "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" + "\"" + ": learning management system" + "\u001B[0m");
        System.out.println("Please make your choice from the offered options\n"
                + "1. Display lectures (number and title)\n"
                + "2. Add a new lecture\n"
                + "3. Delete a lecture by its number\n"
                + "4. Choose a lecture\n"
                + "0. \u001B[31mExit.\n\u001B[0m");

        try {
            switch (reader.readLine()) {
                case "1":
                    System.out.println("Display all lectures \u001b[36;1m\"+\"" + "\u001B[0m" + " or \u001b[31;1m\"-\" \u001B[0mspecifically some by numbers?" + " enter \u001B[32m\"small\"\u001B[0m to preview lectures");
                    try {
                        switch (reader.readLine().toLowerCase()) {
                            case "+":
                                listOfLectures.getLectureList();
                                System.out.println("The List shown what to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                                lectureOutputMenu();
                                break;
                            case "-":
                                System.out.println("Enter numbers separated by commas");
                                listOfLectures.getLectureList(reader.readLine());
                            case "small":
                                System.out.println("Lecture preview");
                                listOfLectures.getPreviewLectureList();
                            default:

                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                    lectureOutputMenu();
                    break;
                case "2":
                    addingLecture();
                    break;
                case "3":
                    removalLecture();
                    break;
                case "4":
                    subFourthMenu();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                    startLMS();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method for navigation: return to main navigation menu
     */
    private void lectureOutputMenu() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //______________________________________________________________________________________________________________//

    /**
     * adding new lectures
     */
    private void addingLecture()  {
        System.out.println("Entering a new lecture");
        System.out.println("If you want add new lecture only name press \u001b[32;1m\"1\"\u001b[0m or press \u001b[36;1m\"2\"\u001b[0m if you want add name and number of lecture");
        try {
            switch (reader.readLine()) {
                case "1":
                    addingLectureListOnlyName();
                    break;
                case "2":
                    try {
                        System.out.print("Please enter number for new lecture: ");
                        addingLectureListNameAndNumber();

                    } catch (NumberFormatException e) {
                        addingLectureListNameAndNumber();
                    }
                    break;
                default:
                    System.out.println("\u001b[31;1mPlease enter only \"1\" or \"2\"\u001b[0m");
                    addingLecture();
            }
        } catch (IOException e) {
            e.printStackTrace();
            addingLecture();
        }

    }

    private void addingLectureListNameAndNumber() {
        System.out.println("Number: ");
        int numberOfLecture = 0;
        String namelect;
        try {
            numberOfLecture = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.print("\u001B[31m" + "wrong number format " + "\u001B[0m \n");
            System.out.println("Lets try again ");
            addingLectureListNameAndNumber();
        }
        try {
            System.out.println("Enter the title of the lecture");
            namelect = reader.readLine();
            listOfLectures.addLecture(numberOfLecture, namelect);
            subMenuAddingLectureListNameAndNumb();
        } catch (IOException e) {
            System.out.print("\u001B[31m" + "Something wrong " + "\u001B[0m \n" + "Lets try again ");
            addingLectureListNameAndNumber();
        }

    }

    private void subMenuAddingLectureListNameAndNumb() {
        System.out.println("what to do next: add another one? entering \"+\" or " + "\u001B[32m" + "\"0\"" + "\u001B[0m"
                + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
        try {
            switch (reader.readLine().toUpperCase()) {
                case "+":
                    addingLectureListNameAndNumber();
                case "0":
                    startLMS();
                    break;
                case "EXIT":
                    System.exit(0);
                    break;
                default:
                    System.out.println("you must enter either \"+\" \"0\" or EXIT");
                    lectureOutputMenu();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void addingLectureListOnlyName() {
        try {
            System.out.println("Entering name a new lecture");
            listOfLectures.addLecture(reader.readLine());
            System.out.println("what to do next: add another one? entering \"+\"" + "\u001B[32m" + " \"0\"" + "\u001B[0m"
                    + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
            subMenuAddingLectureListOnlyName();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void subMenuAddingLectureListOnlyName() {
        try {
            switch (reader.readLine().toUpperCase()) {
                case "+":
                    addingLectureListOnlyName();
                case "0":
                    startLMS();
                    break;
                case "EXIT":
                    System.exit(0);
                    break;
                default:
                    System.out.println("you must enter either \"+\" \"0\" or EXIT");
                    lectureOutputMenu();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //_______________________________________________________________________________________________________________//

    /**
     * method deleting the lecture list
     */

    private void removalLecture() {
        System.out.println("Please enter the number of the lecture you want to delete one or more comma separated ");
        String numbRemovalLecture = null;
        try {
            numbRemovalLecture = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert numbRemovalLecture != null;
            listOfLectures.removeLectures(Integer.parseInt(numbRemovalLecture));
            subMenuRemovalLecture();
        } catch (NumberFormatException e) {
            listOfLectures.removeLectures(numbRemovalLecture);
            subMenuRemovalLecture();
        }
    }

    private void subMenuRemovalLecture() {
        System.out.println("Do you want to delete another one? \n" +
                "if yes then enter " + "\u001B[31m" + "\"+\" " +
                "\u001B[0m" + "if you wont return to the menu " + "\u001B[32m" + "\"-\"" +
                "\u001B[0m" + "" + " or \u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
        try {
            switch (reader.readLine().toUpperCase()) {
                case "+":
                    removalLecture();
                    break;
                case "-":
                    startLMS();
                    break;
                case "EXIT":
                    System.exit(0);
                    break;
                default:
                    System.out.println("please make the right choice \"+\" \"-\" or \"EXIT\"");
                    subMenuRemovalLecture();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void subFourthMenu() {
        System.out.println("Enter the number of the lecture, " +
                "information about which you want to see " +
                "if you change your mind to exit to the menu enter " + "\u001B[32m" + "0" + "\u001B[0m");

        int numbOfLecture = 0;
        boolean flag = true;
        while (flag) {
            try {
                numbOfLecture = Integer.parseInt(reader.readLine());
                flag = false;
            } catch (NumberFormatException | IOException e) {
                System.out.println("You only need to enter a number!");
            }
        }

        if (numbOfLecture == 0) {
            startLMS();
        } else {
            if (numbOfLecture > listOfLectures.getArrLectureLength()) {
                System.out.println("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again");
                subFourthMenu();
            } else {
                listOfLectures.setSelectedLecture(numbOfLecture - 1);
                System.out.println("Selected lecture " + "\u001B[35m" + "\"" + listOfLectures.getNameSelectedLecture() + "\"" + "\u001B[0m");
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

    private void subFourthMenu2() {
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                choice = Integer.parseInt(reader.readLine());
                flag = false;
            } catch (NumberFormatException | IOException e) {
                System.out.println("You only need to enter a number!");

            }
        }
        switch (choice) {
            case 1: // 1. --> choose another lecture
                subFourthMenu();
                break;
            case 2://  2. --> view the list of literature
                listOfLectures.getListLit(listOfLectures.getSelectedLecture());
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

    private void addLit() {
        try {
            System.out.println("Please enter the title of the new book");
            listOfLectures.addNewLiterature(reader.readLine());
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteLit() {
        System.out.println("Please enter the number of the book you want to delete");
        try {
            listOfLectures.removeLiterature(Integer.parseInt(reader.readLine()));
            subFourthMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
