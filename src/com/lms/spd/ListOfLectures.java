package com.lms.spd;

public class ListOfLectures {

    private static String[][] lecture = {
            {/*0*/"Java Core", "Head First Java", "Java. Руководство для начинающих"},
            {/*1*/"Core Java API", "Java. Полное руководство", "Java SE 9. Базовый курс", "Java. Методы программирования"},
            {/*2*/"Class design", "Effective Java", "Философия Java"},
            {/*3*/"Test"}};

    // METHODS FOR WORKING WITH MASSIVE LECTURE

    /**
     * The method prints the list of lectures to the console
     */
    public void getLectureList() {
        for (int i = 0; i < lecture.length; i++) {
            int numbOfLecture = i + 1;
            String nameOfLecture = lecture[i][0];
            System.out.println("Lecture №" + numbOfLecture + ". " + nameOfLecture);
        }
        System.out.println(" ");
    }

    /**
     * Adds a new lecture to an array
     */
    public void addLecture(String lectureAddName) {
        String[][] newArray = new String[lecture.length + 1][];
        // из    начиная с  в новый  начиная с  по предпоследний (последний пустой)
        System.arraycopy(lecture, 0, newArray, 0, newArray.length - 1);

        String[] newLecture = {lectureAddName};
        newArray[newArray.length - 1] = newLecture;
        lecture = newArray;
    }

    /**
     * Remove lecture from array
     * */
    public void removeLecture(int lectureRemove) {
        String[][] newArray1 = new String[lectureRemove - 1][];
        System.arraycopy(lecture, 0, newArray1, 0, newArray1.length);

        String[][] newArray2 = new String[lecture.length - (lectureRemove)][];
        System.arraycopy(lecture, lectureRemove, newArray2, 0, newArray2.length);

        String[][] newArray3 = new String[lecture.length - 1][];
        System.arraycopy(newArray1, 0, newArray3, 0, newArray1.length);
        System.arraycopy(newArray2, 0, newArray3, newArray1.length, newArray2.length);

        lecture = newArray3;
    }

}
