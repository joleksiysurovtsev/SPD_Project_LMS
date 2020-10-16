package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ListOfLectures {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private int selectedLectures;

    static public Lectures[] lectures = {
            new Lectures(1, "Java Core"),
            new Lectures(2, "Class design"),
            new Lectures(5, "Core Java API"),
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
        }
        arrayAddedLectures[arrayAddedLectures.length - 1] = addedLecture;

        lectures = arrayAddedLectures;
        sortLectureArr();
    }


    /**
     * Adds a new lecture by number and name, to an array
     */
    void addLecture(int lectureNumb, String lectureAddName) throws IOException {
        Lectures[] arrayAddedLectures = new Lectures[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);
        Lectures addedLecture;
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
                throw new IllegalStateException("Unexpected value: " + reader.readLine());
        }
        arrayAddedLectures[arrayAddedLectures.length - 1] = addedLecture;

        lectures = arrayAddedLectures;
        sortLectureArr();
    }


    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    void removeLectures(int lectureRemove) {
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


    void removeLectures(String lectureRemove) {
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
    int getSelectedLecture() {
        return selectedLectures;
    }

    /**
     * the method throws the number of the selected lecture into the variable
     */
    void setSelectedLecture(int selected) {
        selectedLectures = selected;
    }

    /**
     * the method returns the lectures amt
     */
    int getArrLectureLength() {
        return lectures.length;
    }

    /**
     * the method returns the name of the lecture that we selected earlier
     */
    String getNameSelectedLecture() {
        int x = getSelectedLecture();
        return lectures[x].toString();
    }

    /**
     * the method returns a list of references from the previously selected lecture
     */
    void getListLit(int numbLecture) {
        lectures[numbLecture].printLitList();
    }

    /**
     * the method adds new literature to the previously selected lecture
     */
    void addNewLiterature(String newLit) {
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
                if (sortedArr[i].getNumberOfLectures() > sortedArr[i + 1].getNumberOfLectures()) {
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
}
