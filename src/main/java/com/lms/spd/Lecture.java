package com.lms.spd;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Lecture {
    private String nameOfLectures;
    private String specialistsName;
    private int numberOfLectures;
    private Literature[] literature;
    private Date lectureDate;

    //lecture constructor____________________________________________________________________________________________//

    /**
     * lecture constructor with name only
     */
    public Lecture(String nameOfLectures) {
        this.nameOfLectures = nameOfLectures;
        this.literature = new Literature[0];
    }

    /**
     * lecture constructor with number and name
     */
    public Lecture(int numberOfLectures, String nameOfLectures) {
        this.nameOfLectures = nameOfLectures;
        this.numberOfLectures = numberOfLectures;
        this.literature = new Literature[0];
    }

    /**
     * lecture constructor with name and literature
     */
    public Lecture(int numberOfLectures, String nameOfLectures, Literature... lit) {
        this.nameOfLectures = nameOfLectures;
        this.numberOfLectures = numberOfLectures;
        this.literature = lit;
    }


    //_______________________________________________________________________________________________________________//


    public void setNameOfLectures(String nameOfLectures) {
        this.nameOfLectures = nameOfLectures;
    }

    public String getNameOfLectures() {
        return nameOfLectures;
    }

    public int getNumberOfLectures() {
        return this.numberOfLectures;
    }

    public void setNumberOfLectures(int numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }

    public Literature[] getLiterature() {
        return literature;
    }

    public void setLiterature(Literature[] literature) {
        this.literature = literature;
    }

    @Override
    public String toString() {
        return "Lecture №" + numberOfLectures +
                ". " + nameOfLectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return numberOfLectures == lecture.numberOfLectures &&
                nameOfLectures.equals(lecture.nameOfLectures) &&
                Arrays.equals(literature, lecture.literature);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nameOfLectures, numberOfLectures);
        result = 31 * result + Arrays.hashCode(literature);
        return result;
    }
}
