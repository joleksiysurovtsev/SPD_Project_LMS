package com.lms.spd;

public class ListOfLectures {

    private int selectedLecture;

    private String[][] lecture = {
            {/*0*/"Java Core", "Head First Java", "Java. Руководство для начинающих"},
            {/*1*/"Core Java API", "Java. Полное руководство", "Java SE 9. Базовый курс", "Java. Методы программирования"},
            {/*2*/"Class design", "Effective Java", "Философия Java"},
            {/*3*/"Test"}};

    // METHODS FOR WORKING WITH MASSIVE LECTURE

    /**
     * The method prints the list of lectures to the console
     */
    void getLectureList() {
        for (int i = 0; i < lecture.length; i++) {
            int numbOfLecture = i + 1;
            String nameOfLecture = lecture[i][0];
            System.out.println("Lecture №" + numbOfLecture + ". " + nameOfLecture);
        }
        System.out.println("______________________");
    }

    /**
     * Adds a new lecture to an array
     */
    void addLecture(String lectureAddName) {
        String[][] newArray = new String[lecture.length + 1][];
        System.arraycopy(lecture, 0, newArray, 0, newArray.length - 1);

        String[] newLecture = {lectureAddName};
        newArray[newArray.length - 1] = newLecture;
        lecture = newArray;
    }

    /**
     * Remove lecture from array
     */
    void removeLecture(int lectureRemove) {
        String[][] newArray1 = new String[lectureRemove - 1][];
        System.arraycopy(lecture, 0, newArray1, 0, newArray1.length);

        String[][] newArray2 = new String[lecture.length - (lectureRemove)][];
        System.arraycopy(lecture, lectureRemove, newArray2, 0, newArray2.length);

        String[][] newArray3 = new String[lecture.length - 1][];
        System.arraycopy(newArray1, 0, newArray3, 0, newArray1.length);
        System.arraycopy(newArray2, 0, newArray3, newArray1.length, newArray2.length);

        lecture = newArray3;
    }


    /*
      methods block of working with a specific lecture
      */


    /**
     * the method throws the number of the selected lecture into the variable
     */
    void setSelectedLecture(int selected) {
        selectedLecture = selected;
    }

    /**
     * the method gets from the variable the number of the selected lecture
     */
    int getSelectedLecture() {
        return selectedLecture;
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
    public void removeLiterature(int indexLecture) {
        String[] result = new String[lecture[selectedLecture].length - 1];
        if (indexLecture > result.length) {
            System.out.println("The book with this number does not exist in the list");
        } else {
            for (int i = 0, j = 0; i < lecture[selectedLecture].length - 1; i++, j++) {
                if (i == indexLecture) {
                    j++;
                }
                result[i] = lecture[selectedLecture][j];
            }
            lecture[selectedLecture] = result;
        }
    }

}
