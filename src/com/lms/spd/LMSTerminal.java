package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LMSTerminal {
    static ListOfLectures ls = new ListOfLectures();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void startLMS() throws IOException {
        System.out.println("\u001B[34m" + "Welcome to the " + "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" + "\"" + ": learning management system\n" + "\u001B[0m");
        getMainMenu();

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

                break;

            default:
                throw new IllegalStateException("Unexpected value: " + reader.readLine());
        }

    }


    /**
     * Method of calling the terminal main menu
     */
    private static void getMainMenu() {
        System.out.println("Please make your choice from the offered options\n"
                + "1. Display all available lectures (number and title)\n"
                + "2. Add a new lecture\n"
                + "3. Delete a lecture by its number\n"
                + "4. Choose a lecture\n"
                + "0. \u001B[31mExit.\n\u001B[0m");
    }

    /**
     * menu for working with the selected lecture
     */
    private static void choiceOfLecture() {
        System.out.println("введите номер лекции, информацию о которой вы хотите посмотреть если передумали для выхода в меню введите " + "\u001B[31m" + "0" + "\u001B[0m");
        System.out.println("Какие дальнейшие дейсвия?");
        System.out.println("1. --> просмотреть список литературы");
        System.out.println("2. --> добавить новую (название / ссылка)");
        System.out.println("3. --> удалить литературу");
        System.out.println("4. --> выйти в меню на уровень выше");
    }

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


}
