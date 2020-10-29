package com.lms.spd;


import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.ArrayList;
import java.util.Arrays;

public class LMSConsolePrinter {
    /**
     * The method prints the list of all lectures to the console
     */
    public void printLectureList(Lecture[] lectures) {
        for (Lecture value : lectures) {
            System.out.println(value);
        }
    }

    /**
     * The method print Preview Lecture list
     */
    public void printPreviewLectureList(Lecture[] lectures) {
        for (Lecture value : lectures) {
            if (value.toString().length() > 50) {
                System.out.println(value.toString().substring(0, 50));
            } else {
                System.out.println(value);
            }
        }
    }

    /**
     * The method prints the list lectures to the console by number
     */
    public void printLectureList(String s, Lecture[] lectures) {
        String[] strings = s.replaceAll("\\s+", "").split(",(?!\\s)");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll("[a-zA-Zа-яА-Я]*", "");
        }
        String[] numbToDisplay = Arrays.stream(strings).filter(x -> !(x.isEmpty())).toArray(String[]::new);
        //iterate over the array of lectures and output if there are matches by lecture numbers
        for (String value : numbToDisplay) {
            for (int j = 0; j < lectures.length; j++) {
                Lecture item = lectures[j];
                if (Integer.parseInt(value) == (j + 1)) {
                    System.out.println(item);
                }
            }
        }
    }


    /**
     * the method print a list of references from the previously selected lecture
     */
    public void printListLit(Lecture selectedLecture) {
        ArrayList<Literature> litArr = selectedLecture.getLiteratures();
        if (litArr.size() > 0) {
            int i = 1;
            for (Literature x : litArr
            ) {
                System.out.print(i + "." );
                x.print();
                i++;
            }
        } else {
            System.out.println("\u001B[31m" + "Lecture is empty, first add literature to it" + "\u001B[0m");
        }
    }

    public void showStartMenu() {
        System.out.println("\u001B[34m" + "Main menu " + "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" + "\"" + ": learning management system" + "\u001B[0m");
        System.out.println("Please make your choice from the offered options\n"
                + "1. Display lectures (number and title)\n"
                + "2. Add a new lecture\n"
                + "3. Delete a lecture by its number\n"
                + "4. Choose a lecture\n"
                + "0. \u001B[31mExit.\n\u001B[0m");
    }

    /**
     * point 4 main menu: method deleting the lecture list
     */

    void showFourthMenu() {
        System.out.println("1. --> choose another lecture");
        System.out.println("2. --> view the list of literature");
        System.out.println("3. --> add new literature");
        System.out.println("4. --> remove literature");
        System.out.println("5. --> view all lecture information");
        System.out.println("6. --> exit to the main menu");
    }
}
