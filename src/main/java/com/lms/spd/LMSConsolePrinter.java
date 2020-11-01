package com.lms.spd;


import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class LMSConsolePrinter {
    /**
     * The method prints the list of all lectures to the console
     */
    public void printLectureList(List<Lecture> lectures) {
        lectures.forEach(System.out::println);
    }

    /**
     * The method print Preview Lecture list
     */
    public void printPreviewLectureList(List<Lecture> lectures) {
        lectures.forEach(value -> {
            if (value.toString().length() > 50) {
                System.out.println(value.toString().substring(0, 50));
            } else {
                System.out.println(value);
            }
        });
    }

    /**
     * The method prints the list lectures to the console by number
     */
    public void printLectureList(String s, List<Lecture> lectures) {
        String[] numbToDisplay = getStringsNumberLect(s);
        Arrays.stream(numbToDisplay).forEach(value -> lectures.stream()
                .filter(x -> Integer.parseInt(value) == x.getNumberOfLecture())
                .forEach(System.out::println));
    }


    private String[] getStringsNumberLect(String s) {
        String[] strings = s.replaceAll("\\s+", "").split(",(?!\\s)");
        IntStream.range(0, strings.length).forEach(i -> strings[i] = strings[i].replaceAll("[a-zA-Zа-яА-Я]*", ""));
        return Arrays.stream(strings).filter(x -> !(x.isEmpty())).toArray(String[]::new);
    }


    /**
     * the method print a list of references from the previously selected lecture
     */
    public void printListLit(Lecture selectedLecture) {
        List<Literature> litArr = selectedLecture.getLiteratures();
        if (!litArr.isEmpty()) {
            int i = 1;
            for (Literature x : litArr) {
                System.out.println(i + x.print());
                i++;
            }
        } else {
            System.out.println("\u001B[31m" + "Lecture is empty, first add literature to it" + "\u001B[0m");
        }
    }


    public void showStartMenu() {
        System.out.println("\u001B[34m" + "Main menu " +
                "\"\u001B[32mL\u001B[35mM\u001B[31mS\u001B[34m" +
                "\"" + ": learning management system" + "\u001B[0m\n" +
                "\"Please make your choice from the offered options\n"
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
        System.out.println("1. --> choose another lecture\n" +
                "\"2. --> view the list of literature\n" +
                "\"3. --> add new literature\n" +
                "\"4. --> remove literature\n" +
                "\"5. --> view all lecture information\n" +
                "\"6. --> exit to the main menu");
    }
}
