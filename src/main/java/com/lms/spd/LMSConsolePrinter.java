package com.lms.spd;


import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

public class LMSConsolePrinter {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * The method prints the list of all lectures to the console if the date of the lecture has passed then the lecture is crossed out
     */
    public void printLectureList(List<Lecture> lectures) {
        if (lectures.isEmpty()) {
            System.out.println("The lecture list is empty first add lectures");
        } else {
            lectures.forEach(this::printFullLectList);
        }
    }


    /**
     * The method print Preview Lecture list
     */
    public void printPreviewLectureList(List<Lecture> lectures) {
        if (lectures.isEmpty()) {
            System.out.println("The lecture list is empty first add lectures");
        } else {
            lectures.forEach(this::printSubLectList);
        }
    }

    /**
     * The method prints the list lectures to the console by number
     */
    public void printLectureList(String s, List<Lecture> lectures) {
        if (lectures.isEmpty()) {
            System.out.println("The lecture list is empty first add lectures");
            return;
        }
        String[] numbToDisplay = getStringsNumberLect(s);
        Arrays.stream(numbToDisplay).forEach(value -> lectures.stream()
                .filter(x -> Integer.parseInt(value) == x.getNumberOfLecture())
                .forEach(this::printFullLectList));
    }

    private void printSubLectList(Lecture value) {
        String nameLectures = value.getNameOfLecture().length() > 30 ? value.getNameOfLecture().substring(0, 30) : value.getNameOfLecture();
        System.out.println(value.getLectureDate().before(Calendar.getInstance()) ? ("\u001B[31m" + "\u001b[9m" + "Date: " + sdf.format(value.getLectureDate().getTime()) + " Lecture №" + value.getNumberOfLecture() + "    Title:  " + nameLectures + "\u001B[0m") : ("Date: " + sdf.format(value.getLectureDate().getTime()) + " Lecture №" + value.getNumberOfLecture() + "    Title:  " + nameLectures));
    }

    private void printFullLectList(Lecture value) {
        System.out.println(value.getLectureDate().before(Calendar.getInstance()) ? "\u001B[31m" + "\u001b[9m" + "Date: " + sdf.format(value.getLectureDate().getTime()) + " Lecture №" + value.getNumberOfLecture() + "    Title:  " + value.getNameOfLecture() + "\u001B[0m" : "Date: " + sdf.format(value.getLectureDate().getTime()) + " Lecture №" + value.getNumberOfLecture() + "    Title:  " + value.getNameOfLecture());
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
        if (litArr.isEmpty()) {
            System.out.println("\u001B[31m" + "Lecture is empty, first add literature to it" + "\u001B[0m");
        } else {
            int i = 1;
            for (Literature x : litArr) {
                System.out.println(i + x.print());
                i++;
            }
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

    public void printMessagesAddLit(int message) {
        if (message == 1) {
            System.out.println("Enter a title");
        }
        if (message == 2) {
            System.out.println("Please enter a author name");
        }
        if (message == 3) {
            System.out.println("Please enter a titleJournal name or press Enter");
        }
        if (message == 4) {
            System.out.println("Please enter a issue of the journal where the article was published");
        }
        if (message == 5) {
            System.out.println("Please enter a url address or press Enter");
        }
        if (message == 6) {
            System.out.println("Please enter a genre name or press Enter");
        }
        if (message == 7) {
            System.out.println("Please enter a year of publication of the book");
        }
    }



}
