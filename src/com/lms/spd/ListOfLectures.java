package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ListOfLectures {
    static public Lectures[] lectures = {
            new Lectures(1, "BufferedReader - Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines."),
            new Lectures(3, "Writes text to a character-output stream, buffering characters so as to provide for the efficient writing of single characters, arrays, and strings."),
            new Lectures(5, "Core Java API"),
            new Lectures(7, "Core Java API"),
    };
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Lectures selectedLectures;


    // METHODS FOR WORKING WITH MASSIVE LECTURE

//_________________________________________________________________________________________________________________//

    /**
     * The method prints the list of all lectures to the console
     */
    public void getLectureList() {
        for (Lectures value : lectures) {
            System.out.println(value.toString());
        }
        System.out.println("______________________");
    }

    /**
     * The method print Preview Lecture list
     */
    void getPreviewLectureList() {
        for (Lectures value : lectures) {
            if (value.toString().length() > 50) {
                System.out.println(value.toString().substring(0, 50));
            } else {
                System.out.println(value.toString());
            }
        }
        System.out.println("______________________");
    }


    /**
     * The method prints the list lectures to the console by number
     */
    public void getLectureList(String s) {
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
                }
            }
        }


    }
//_________________________________________________________________________________________________________________//

    /**
     * Adds a new lecture by only name, to an array
     */
    public void addLecture(String lectureAddName) throws IOException {
        Lectures[] arrayAddedLectures = new Lectures[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);
        Lectures addedLecture = new Lectures(lectureAddName);
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");
        switch (reader.readLine()) {
            case "+":
                System.out.println("Enter literature separated by commas");
                String literatures = reader.readLine();
                String[] strings = literatures.replaceAll("\\s+", "").split(",(?!\\s)");
                addedLecture = new Lectures((lectureAddName), strings);
                break;
            case "-":
                addedLecture = new Lectures(lectureAddName);
                break;
            default:
                System.out.println("Something wrong");
                addLecture(lectureAddName);
        }
        arrayAddedLectures[arrayAddedLectures.length - 1] = addedLecture;

        lectures = arrayAddedLectures;
        sortLectureArr();
    }


    /**
     * Adds a new lecture by number and name, to an array
     */
    public void addLecturePlusNumber(int lectureNumb, String lectureAddName) throws IOException {
        Lectures[] arrayAddedLectures = new Lectures[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);
        Lectures addedLecture = new Lectures(lectureNumb,lectureAddName);
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");
        switch (reader.readLine()) {
            case "+":
                System.out.println("Enter literature separated by commas");
                String literatures = reader.readLine();
                String[] strings = literatures.replaceAll("\\s+", "").split(",(?!\\s)");

                addedLecture = new Lectures(lectureNumb, (lectureAddName), strings);
                break;
            case "-":
                addedLecture = new Lectures(lectureNumb, lectureAddName);
                break;
            default:
                System.out.println("Something wrong");
                //addLecturePlusNumber(lectureNumb,lectureAddName);
        }
        arrayAddedLectures[arrayAddedLectures.length - 1] = addedLecture;

        lectures = arrayAddedLectures;
        sortLectureArr();
    }


    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    public void removeLectures(int lectureRemove) {
        boolean flag = false;
        for (Lectures value : lectures) {
            int numb = value.getNumberOfLectures();
            if (numb == lectureRemove) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println("Lectures under this number do not exist");
            System.out.println("try again");
            return;
        }
        System.out.println("successfully");
        lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLectures() == lectureRemove)).toArray(Lectures[]::new);
    }


    public void removeLectures(String lectureRemove) {
        String[] strings = lectureRemove.replaceAll("\\s+", "").split(",(?!\\s)");

        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll("[a-zA-Zа-яА-Я]*", "");
        }

        String[] numbToDisplay = Arrays.stream(strings).filter(x -> !(x.isEmpty())).toArray(String[]::new);
        StringBuilder stringContains = new StringBuilder("Lectures by numbers: ");

        for (String item : numbToDisplay) {
            for (Lectures value : lectures) {
                int numb = value.getNumberOfLectures();
                if (numb == Integer.parseInt(item)) {
                    stringContains.append("\u001b[33;1m\"").append(item).append("\"\u001b[0m, ");
                    break;
                }
            }
        }
        stringContains.append("successfully removed the rest are missing.");
        System.out.println(stringContains);

        for (String s : numbToDisplay) {
            int z = Integer.parseInt(s);
            lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLectures() == z)).toArray(Lectures[]::new);
        }
    }


    //______________________________________________________________________________________________________________//

    // methods block of working with a specific lecture

    /**
     * the method gets from the variable the number of the selected lecture
     */
    public int getSelectedLecture() {
        return selectedLectures.getNumberOfLectures();
    }

    /**
     * the method throws the number of the selected lecture into the variable
     */
    public void setSelectedLecture(int selected) {
        //
        Lectures select = new Lectures("");

        for (Lectures lecture : lectures) {
            if (lecture.getNumberOfLectures() == selected+1) {
                select = lecture;
                break;
            }
        }


        selectedLectures = select;
    }

    /**
     * the method returns the name of the lecture that we selected earlier
     */
    public String getNameSelectedLecture() {
        return selectedLectures.toString();
    }

    /**
     * the method returns a list of references from the previously selected lecture
     */
    public void getListLit(int numbLecture) {
        lectures[numbLecture].printLitList();
    }

    /**
     * the method adds new literature to the previously selected lecture
     */
    public void addNewLiterature(String newLit) {
        lectures[getSelectedLecture()].addNewLit(newLit);
    }

    /**
     * method removes literature by number from a previously selected lecture
     */
    public void removeLiterature(int indexLit) {
        lectures[getSelectedLecture()].deleteLit(indexLit);
    }


    public void sortLectureArr() {
        Lectures[] sortedArr = lectures;
        boolean sorted = false;
        Lectures temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < lectures.length - 1; i++) {
                if (sortedArr[i].getNumberOfLectures() >= sortedArr[i + 1].getNumberOfLectures()) {
                    temp = sortedArr[i];
                    sortedArr[i] = sortedArr[i + 1];
                    sortedArr[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 0; i < lectures.length - 1; i++) {
                if (sortedArr[i].getNumberOfLectures() == sortedArr[i + 1].getNumberOfLectures()) {
                    sortedArr[i + 1].setNumberOfLectures((sortedArr[i + 1].getNumberOfLectures()) + 1);
                    flag = false;
                }
            }
        }
        lectures = sortedArr;
    }

    //проверка есть ли номер такой лекции
    public boolean checkNumberLecture(int numbOfLectures) {
        boolean flag = false;
        for (Lectures lecture : lectures) {
            if (lecture.getNumberOfLectures() == numbOfLectures) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
