package com.lms.spd;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LMSTerminal {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ListOfLectures listOfLectures = new ListOfLectures();

    private void showStartMenu() {
        System.out.println("\u001B[34m" + "Main menu " + "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" + "\"" + ": learning management system" + "\u001B[0m");
        System.out.println("Please make your choice from the offered options\n"
                + "1. Display lectures (number and title)\n"
                + "2. Add a new lecture\n"
                + "3. Delete a lecture by its number\n"
                + "4. Choose a lecture\n"
                + "0. \u001B[31mExit.\n\u001B[0m");
    }

    public void startLMS() {
        showStartMenu();
        try {
            switch (reader.readLine()) {
                case "1":
                    point1MainMenuShowLectures();
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
                default:
                    System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                    startLMS();
                    break;
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    //______________________________________________________________________________________________________________//

    /**
     * point 1 main menu: view a list of lectures
     */
    private void point1MainMenuShowLectures() throws IOException {
        System.out.println("Display all lectures \u001b[36;1m\"+\"" + "\u001B[0m" + " or \u001b[31;1m\"-\" \u001B[0mspecifically some by numbers?" + " enter \u001B[32m\"small\"\u001B[0m to preview lectures");
        switch (reader.readLine().toLowerCase()) {
            case "+":
                listOfLectures.getLectureList();
                System.out.println("The List shown what to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                break;
            case "-":
                System.out.println("Enter numbers separated by commas");
                listOfLectures.getLectureList(reader.readLine());
                System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                break;
            case "small":
                System.out.println("Lecture preview");
                listOfLectures.getPreviewLectureList();
                System.out.println("What to do next:" + "\u001B[32m" + " \"0\"" + "\u001B[0m" + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
                break;
            default:
                point1MainMenuShowLectures();
                break;
        }
        subMenuShowLectures();
    }

    private void subMenuShowLectures() throws IOException {
        switch (reader.readLine().toUpperCase()) {
            case "0":
                startLMS();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("you must enter either \"0\" or EXIT");
                subMenuShowLectures();
                break;
        }
    }

    //______________________________________________________________________________________________________________//

    /**
     * point 2 main menu: adding new lectures
     */
    private void point2MainMenuAddingLecture() throws IOException {
        System.out.println("Entering a new lecture");
        System.out.println("If you want add new lecture only name press \u001b[32;1m\"1\"\u001b[0m or press \u001b[36;1m\"2\"\u001b[0m if you want add name and number of lecture");
        switch (reader.readLine()) {
            case "1":
                addingLectureListOnlyName();
                break;
            case "2":
                addingLectureListNameAndNumber();
                break;
            default:
                System.out.println("\u001b[31;1mPlease enter only \"1\" or \"2\"\u001b[0m");
                point2MainMenuAddingLecture();
        }
    }

    private void addingLectureListOnlyName() throws IOException {
        System.out.println("Entering name a new lecture");
        listOfLectures.addLecture(reader.readLine());
        System.out.println("what to do next: add another one? entering \"+\"" + "\u001B[32m" + " \"0\"" + "\u001B[0m"
                + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
        subMenuAddingLectureListOnlyName();
    }

    private void subMenuAddingLectureListOnlyName() throws IOException {
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
                subMenuAddingLectureListOnlyName();
                break;
        }
    }

    private void addingLectureListNameAndNumber() {
        System.out.print("Please enter number for new lecture, ");
        System.out.println("Number: ");
        int numberOfLecture = 0;
        try {
            numberOfLecture = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.print("\u001B[31m" + "wrong number format " + "\u001B[0m \n");
            System.out.println("Lets try again ");
            addingLectureListNameAndNumber();
        }
        try {
            System.out.println("Enter the title of the lecture");
            listOfLectures.addLecture(numberOfLecture, reader.readLine());
            subMenuAddingLectureListNameAndNumb();
        } catch (IOException e) {
            System.out.print("\u001B[31m" + "Something wrong " + "\u001B[0m \n" + "Lets try again ");
            addingLectureListNameAndNumber();
        }

    }

    private void subMenuAddingLectureListNameAndNumb() throws IOException {
        System.out.println("what to do next: add another one? entering \"+\" or \"-\" add lecture by only name" + "\u001B[32m" + "\"0\"" + "\u001B[0m"
                + " go to the main menu or " + "\u001B[31m" + "\"EXIT\"" + "\u001B[0m" + " end the program");
        switch (reader.readLine().toUpperCase()) {
            case "+":
                addingLectureListNameAndNumber();
            case "-":
                addingLectureListOnlyName();
            case "0":
                startLMS();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("you must enter either \"+\" \"-\" \"0\" or EXIT");
                subMenuShowLectures();
                break;
        }
    }

    //_______________________________________________________________________________________________________________//

    /**
     * point 3 main menu: method deleting the lecture list
     */

    private void point3MainMenuRemovalLecture() throws IOException {
        System.out.println("Please enter the number of the lecture if you want to delete one or more comma separated ");
        String numbRemovalLecture = null;
        try {
            numbRemovalLecture = reader.readLine();
        } catch (IOException e) {
            point3MainMenuRemovalLecture();
            e.printStackTrace();
        }
        try {
            assert numbRemovalLecture != null;
            listOfLectures.removeLectures(Integer.parseInt(numbRemovalLecture));
            subMenuRemovalLecture();
        } catch (NumberFormatException | IOException e) {
            listOfLectures.removeLectures(numbRemovalLecture);
            subMenuRemovalLecture();
        }
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
    }

    //_______________________________________________________________________________________________________________//

    /**
     * point 4 main menu: method deleting the lecture list
     */

    private void showFourthMenu() {
        System.out.println("1. --> choose another lecture");
        System.out.println("2. --> view the list of literature");
        System.out.println("3. --> add new literature");
        System.out.println("4. --> remove literature");
        System.out.println("5. --> exit to the main menu");
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
                numbOfLecture = Integer.parseInt(reader.readLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("You must type a lecture number!");
            }
        }

        if (numbOfLecture == 0) {
            startLMS();
        } else {

            if (!listOfLectures.checkNumberLecture(numbOfLecture)) {
                System.out.println("\u001B[31m" + "There is no such lecture" + "\u001B[0m" + "\nlet's try again");
                point4MainMenuChoiceOfLecture();
            } else {
                listOfLectures.setSelectedLecture(numbOfLecture - 1);
                System.out.println("Selected lecture " + "\u001B[35m" + "\"" + listOfLectures.getNameSelectedLecture() + "\"" + "\u001B[0m");
                System.out.println("What are the next actions?");
                System.out.println("__________________________");
            }
        }
        showFourthMenu();
        subMenu2Point4();
    }

    private void subMenu2Point4() throws IOException {
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
            case 5:
                startLMS();
                break;
            default:
                System.out.println("\u001B[31m" + "There is no such item in the menu, let's try again" + "\u001B[0m");
                subMenu2Point4();
                break;
        }
    }

    private void point4_2ViewListOfLit() throws IOException {
        listOfLectures.getListLit(listOfLectures.getSelectedLecture());
        System.out.println("_______________________");
        System.out.println("what do we do with the bibliography");
        showFourthMenu();
        subMenu2Point4();
    }

    private void point4_3AddLit() throws IOException {

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

        listOfLectures.removeLiterature(Integer.parseInt(reader.readLine()));
        System.out.println("Delete again ?");
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
}
