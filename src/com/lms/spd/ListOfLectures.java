package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfLectures {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private int selectedLecture;

    private String[][] lecture = {
            {/*0*/"Java Core", "Head First Java", "Java. Руководство для начинающих"},
            {/*1*/"Core Java API", "Java. Полное руководство", "Java SE 9. Базовый курс", "Java. Методы программирования"},
            {/*2*/"Class design", "Effective Java", "Философия Java"},
            {/*3*/"Test"}
    };

    static public Literature[] literatures = {
            new Literature("\"Effective Java\""),
            new Literature("\"Философия Java\"")
    };


    static public Lectures[] lectures = {
            new Lectures(1, "Java Core"),
            new Lectures(2, "Class design"),
            new Lectures(3, "Core Java API"),
    };


    // METHODS FOR WORKING WITH MASSIVE LECTURE
//_________________________________________________________________________________________________________________//

    /**
     * The method prints the list of all lectures to the console
     */
    void getLectureList() {
        for (Lectures value : lectures) {
            System.out.println(value.toString());
        }
        System.out.println("______________________");
    }

    /**
     * The method prints the list lectures to the console by number
     */
    void getLectureList(String s) {
        String[] strings = s.replaceAll("\\s+", "").split(",(?!\\s)");

        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll("[a-zA-Zа-яА-Я]*", "");
        }

        String[] numbToDisplay = Arrays.stream(strings).filter(x -> !(x.isEmpty())).toArray(String[]::new);

        //iterate over the array of lectures and output if there are matches by lecture numbers
        for (String value : numbToDisplay) {
            for (Lectures item : lectures) {
                if (Integer.parseInt(value) == (item.getNumberOfLectures())) {
                    System.out.println(item.toString());
                    break;
                }
            }
        }
    }
//_________________________________________________________________________________________________________________//

    /**
     * Adds a new lecture by only name, to an array
     */
    void addLecture(String lectureAddName) throws IOException {
        Lectures[] arrayAddedLectures = new Lectures[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);
        Lectures addedLecture = null;
        System.out.println("Добавляем литературу + да - нет");
        switch (reader.readLine()) {
            case "+":
                System.out.println("Введите список литературы через запятую");
                String literatures = reader.readLine();
                String[] strings = literatures.replaceAll("\\s+", "").split(",(?!\\s)");

                Literature[] arrLitAdd = new Literature[strings.length];
                for (int i = 0; i < arrLitAdd.length; i++) {
                    arrLitAdd[i] = new Literature(strings[i]);
                }
                addedLecture = new Lectures((lectureAddName), arrLitAdd);
                break;
            case "-":
                addedLecture = new Lectures(lectureAddName);
                break;
        }
        arrayAddedLectures[arrayAddedLectures.length - 1] = addedLecture;
        lectures = arrayAddedLectures;
    }


//    private Lectures[] sortLectureArr(Lectures[] lArr) {
//        Lectures[] sortedArr = new Lectures[lArr.length];
//        boolean sorted = false;
//        Lectures temp;
//        while (!sorted) {
//            sorted = true;
//            for (int i = 0; i < lArr.length+1; i++) {
//                if (sortedArr[i].getNumberOfLectures() > sortedArr[i + 1].getNumberOfLectures()) {
//                    temp = sortedArr[i];
//                    sortedArr[i] = sortedArr[i + 1];
//                    sortedArr[i + 1] = temp;
//                    sorted = false;
//                }
//            }
//        }
//        return sortedArr;
//    }

    /**
     * Adds a new lecture by number and name, to an array
     */
    void addLecture(int lectureNumb, String lectureAddName) {
        //создали масив на одну больше
        Lectures[] arrayAddedLectures = new Lectures[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);

        Lectures addedLecture = new Lectures(lectureNumb, lectureAddName);

        arrayAddedLectures[arrayAddedLectures.length - 1] = addedLecture;
        lectures = arrayAddedLectures;
    }

    void addLecture(String lectureAddName, Literature... literature) {
        //создали масив на одну больше
        Lectures[] arrayAddedLectures = new Lectures[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);

        Lectures addedLecture = new Lectures(lectureAddName, literature);
        arrayAddedLectures[arrayAddedLectures.length - 1] = addedLecture;
        lectures = arrayAddedLectures;
    }


    /**
     * Remove lecture from array
     */
    void removeLecture(int lectureRemove) {
        Lectures[] tempArrToBeLectureRemove = new Lectures[lectureRemove - 1];
        System.arraycopy(lecture, 0, tempArrToBeLectureRemove, 0, tempArrToBeLectureRemove.length);

        Lectures[] tempArrAfterLectureRemove = new Lectures[lecture.length - (lectureRemove)];
        System.arraycopy(lecture, lectureRemove, tempArrAfterLectureRemove, 0, tempArrAfterLectureRemove.length);

        Lectures[] deletedLectureArr = new Lectures[lecture.length - 1];
        System.arraycopy(tempArrToBeLectureRemove, 0, deletedLectureArr, 0, tempArrToBeLectureRemove.length);
        System.arraycopy(tempArrAfterLectureRemove, 0, deletedLectureArr, tempArrToBeLectureRemove.length, tempArrAfterLectureRemove.length);

        lectures = deletedLectureArr;
    }


    /*
      methods block of working with a specific lecture
      */

    /**
     * the method gets from the variable the number of the selected lecture
     */
    int getSelectedLecture() {
        return selectedLecture;
    }

    /**
     * the method throws the number of the selected lecture into the variable
     */
    void setSelectedLecture(int selected) {
        selectedLecture = selected;
    }

    /**
     * the method returns the number of lectures
     */
    int getArrLectureLength() {
        return lecture.length;
    }

    /**
     * the method returns the name of the lecture that we selected earlier
     */
    String getNameSelectedLecture() {
        int x = getSelectedLecture();
        return lecture[x][0];
    }

    /**
     * the method returns a list of references from the previously selected lecture
     */
    void getListLit(int numbLecture) {
        if (lecture[numbLecture].length == 1) {
            System.out.println("\u001B[31m" + "Lecture is empty, first add literature to it" + "\u001B[0m");
        } else {
            for (int i = 1; i < lecture[numbLecture].length; i++) {
                System.out.println(i + ". " + lecture[numbLecture][i]);
            }
        }
    }

    /**
     * the method adds new literature to the previously selected lecture
     */
    void addNewLiterature(String newLit) {
        String[] newArrLit = new String[lecture[selectedLecture].length + 1];

        System.arraycopy(lecture[selectedLecture], 0, newArrLit, 0, lecture[selectedLecture].length);
        newArrLit[newArrLit.length - 1] = newLit;

        lecture[selectedLecture] = newArrLit;
    }

    /**
     * method removes literature by number from a previously selected lecture
     */
    public void removeLiterature(int indexLit) {
        String[] deletedLitArr = new String[lecture[selectedLecture].length - 1];
        if (indexLit > deletedLitArr.length) {
            System.out.println("The book with this number does not exist in the list");
        } else {
            for (int i = 0, j = 0; i < lecture[selectedLecture].length - 1; i++, j++) {
                if (i == indexLit) {
                    j++;
                }
                deletedLitArr[i] = lecture[selectedLecture][j];
            }
            lecture[selectedLecture] = deletedLitArr;
        }
    }

}
