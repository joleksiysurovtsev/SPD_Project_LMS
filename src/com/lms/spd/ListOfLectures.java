package com.lms.spd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ListOfLectures {


    static public Lecture[] lectures = {
            new Lecture(1, "BufferedReader."),
            new Lecture(2, "Writes text to."),
            new Lecture(3, "Core Java API"),
            new Lecture(4, "Core Java API4"),
    };
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Lecture selectedLecture;

    public Lecture getSelectedLecture() {
        return selectedLecture;
    }
// METHODS FOR WORKING WITH MASSIVE LECTURE

//_________________________________________________________________________________________________________________//

    /**
     * The method prints the list of all lectures to the console
     */
    public void getLectureList() {
        for (Lecture value : lectures) {
            System.out.println(value.toString());
        }
        System.out.println("______________________");
    }

    /**
     * The method print Preview Lecture list
     */
    void getPreviewLectureList() {
        for (Lecture value : lectures) {
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
            for (int j = 0; j < lectures.length; j++) {
                Lecture item = lectures[j];
                if (Integer.parseInt(value) == (j + 1)) {
                    System.out.println(item.toString());
                }
            }
        }
    }
//_________________________________________________________________________________________________________________//

    /**
     * Adds a new lecture by only name, to an array
     */
    public void addLecture() throws IOException {
        String lectureAddName = reader.readLine();

        Lecture[] arrayAddedLectures = new Lecture[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);
        Literature[] arrAddLitZero = new Literature[0];

        Lecture addedLecture = new Lecture(arrayAddedLectures.length, lectureAddName, arrAddLitZero);
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");

        switch (reader.readLine()) {
            case "+":
                System.out.println("Enter literature separated by commas");
                String literatures = reader.readLine();
                String[] strings = literatures.replaceAll("\\s+", "").split(",(?!\\s)");
                Literature[] arrAddLit = new Literature[strings.length];
                for (int i = 0; i < arrAddLit.length; i++) {
                    arrAddLit[i] = new Literature(strings[i]);
                }
                addedLecture = new Lecture(arrayAddedLectures.length, lectureAddName, arrAddLit);
                break;
            case "-":
                break;
            default:
                System.out.println("Something wrong");
                addLecture();
        }
        arrayAddedLectures[arrayAddedLectures.length - 1] = addedLecture;
        lectures = arrayAddedLectures;
    }


    /**
     * Adds a new lecture by number and name, to an array
     */

    public void addLecturePlusNumber() throws IOException {
        int numberOfLecture = enterTheLectureNumber();
        String lectureName = enterTheLectureName();
        Literature[] literatureArr = enterTheLitArr();

        Lecture[] arrayAddedLectures = new Lecture[lectures.length + 1];
        System.arraycopy(lectures, 0, arrayAddedLectures, 0, arrayAddedLectures.length - 1);

        if (numberOfLecture>arrayAddedLectures.length){
            numberOfLecture = arrayAddedLectures.length;
        }

        Lecture addedLecture = new Lecture(numberOfLecture, lectureName, literatureArr);



        if (numberOfLecture <= arrayAddedLectures.length) {
            for (int i = arrayAddedLectures.length - 1; i > -1; i--) {
                if (i == numberOfLecture - 1) {
                    arrayAddedLectures[i] = addedLecture;
                    break;
                }
                arrayAddedLectures[i] = arrayAddedLectures[i - 1];
            }
        }

        lectures = arrayAddedLectures;
        sortLectureArr();

        System.out.println("Successfully");
    }

    private String enterTheLectureName() {
        System.out.println("Enter the title of the lecture");
        String lectureName = "";
        try {
            lectureName = reader.readLine();
        } catch (IOException e) {
            System.out.println("Something wrong try again");
            enterTheLectureName();
        }
        return lectureName;
    }

    private int enterTheLectureNumber() {
        System.out.print("Please enter number for new lecture, ");
        System.out.println("Number: ");
        int numberOfLecture = 0;
        try {
            numberOfLecture = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.print("\u001B[31m" + "wrong number format " + "\u001B[0m \n");
            System.out.println("Lets try again ");
            enterTheLectureNumber();
        }
        return numberOfLecture;
    }

    private Literature[] enterTheLitArr() throws IOException {
        System.out.println("Add literature \u001b[32;1m\" + \"\u001b[0m YES \u001b[35;1m\" - \"\u001b[0m NO");
        Literature[] arrAddLit = new Literature[0];
        String choice = reader.readLine();

        switch (choice) {
            case "+":
                System.out.println("Enter literature separated by commas");

                String literatures = reader.readLine();
                String[] strings = literatures.replaceAll("\\s+", "").split(",(?!\\s)");
                arrAddLit = new Literature[strings.length];
                for (int i = 0; i < arrAddLit.length; i++) {
                    arrAddLit[i] = new Literature(strings[i]);
                }
                break;
            case "-":
                break;
            default:
                break;
        }

        return arrAddLit;
    }

    //______________________________________________________________________________________________________________//

    /**
     * Remove lecture from array
     */
    public void removeLectures(int lectureRemove) {
        boolean flag = false;
        for (Lecture value : lectures) {
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
        lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLectures() == lectureRemove)).toArray(Lecture[]::new);
        sortLectureArrAfterRemove();
    }


    public void removeLectures(String lectureRemove) {
        String[] strings = lectureRemove.replaceAll("\\s+", "").split(",(?!\\s)");

        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll("[a-zA-Zа-яА-Я]*", "");
        }

        String[] numbToDisplay = Arrays.stream(strings).filter(x -> !(x.isEmpty())).toArray(String[]::new);
        StringBuilder stringContains = new StringBuilder("Lectures by numbers: ");

        for (String item : numbToDisplay) {
            for (Lecture value : lectures) {
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
            lectures = Arrays.stream(lectures).filter(x -> !(x.getNumberOfLectures() == z)).toArray(Lecture[]::new);
        }
        sortLectureArrAfterRemove();
        System.out.println("Successfully");
    }


    //______________________________________________________________________________________________________________//

    // methods block of working with a specific lecture

    /**
     * the method throws the number of the selected lecture into the variable
     */
    public void setSelectedLecture(int selected) {
        //
        Lecture select = new Lecture("");

        for (Lecture lecture : lectures) {
            if (lecture.getNumberOfLectures() == selected + 1) {
                select = lecture;
                break;
            }
        }

        selectedLecture = select;
    }

    //_______________________________________________________________________________________________//

    /**
     * the method returns a list of references from the previously selected lecture
     */
    public void getListLit() {
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

    /**
     * the method adds new literature to the previously selected lecture
     */
    public void addNewLiterature(String newBook) {
        Literature[] litArr = selectedLecture.getLiterature();
        Literature newLit = new Literature(newBook);
        Literature[] newArrayLiterature = Arrays.copyOf(litArr, litArr.length + 1);
        newArrayLiterature[newArrayLiterature.length - 1] = newLit;
        selectedLecture.setLiterature(newArrayLiterature);
    }

    /**
     * method removes literature by number from a previously selected lecture
     */
    public void removeLiterature(int indexLit) {
        boolean flag = false;
        if (selectedLecture.getLiterature().length >= indexLit) {
            flag = true;
        }
        if (!flag) {
            System.out.println("Literature under this number do not exist");
            System.out.println("try again");
            return;
        }
        System.out.println("successfully");

        if (selectedLecture.getLiterature().length == 1) {
            selectedLecture.setLiterature(new Literature[0]);
        } else {
            Literature[] literature = selectedLecture.getLiterature();
            Literature litTuDel = literature[indexLit - 1];
            selectedLecture.setLiterature(Arrays.stream(literature).filter(x -> !(x == litTuDel)).toArray(Literature[]::new));
        }
    }
    //________________________________________________________________________________________________//

    public void sortLectureArr() {
        Lecture[] sortedArr = lectures;
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

    private void sortLectureArrAfterRemove() {
        Lecture[] sortedArr = lectures;

        for (int i = 0; i < lectures.length; i++) {
            sortedArr[i].setNumberOfLectures(i + 1);
        }
        lectures = sortedArr;
    }

    public boolean checkNumberLecture(int numbOfLectures) {
        boolean flag = false;
        if (lectures.length - 1 >= numbOfLectures-1) {
            flag = true;
        }

       return flag;
    }
}
