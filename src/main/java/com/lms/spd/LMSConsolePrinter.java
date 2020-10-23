package com.lms.spd;

import java.util.Arrays;

public class LMSConsolePrinter {
    /**
     * The method prints the list of all lectures to the console
     */
    public void printLectureList(Lecture[] lectures) {
        for (Lecture value : lectures) {
            System.out.println(value.toString());
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
                System.out.println(value.toString());
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
                    System.out.println(item.toString());
                }
            }
        }
    }


    /**
     * the method returns a list of references from the previously selected lecture
     */
    public void printListLit(Lecture selectedLecture) {
        Literature[] litArr = selectedLecture.getLiterature();
        if (litArr.length > 0) {
            int i = 1;
            for (Literature x : litArr
            ) {
                System.out.println(i + "." + x.toString());
                i++;
            }
        } else {
            System.out.println("\u001B[31m" + "Lecture is empty, first add literature to it" + "\u001B[0m");
        }
    }
}
