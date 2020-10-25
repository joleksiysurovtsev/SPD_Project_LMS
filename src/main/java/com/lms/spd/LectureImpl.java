package com.lms.spd;

import com.lms.spd.interfaces.Lecture;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class LectureImpl implements Lecture {
    private String nameOfLecture;
    private int numberOfLecture;
    private Literature[] literatures;
    private Date lectureDate;
    private String lectorName;

    //lecture constructor____________________________________________________________________________________________//

    public LectureImpl(String nameOfLecture) {
        this.nameOfLecture = nameOfLecture;
        this.literatures = new Literature[0];
    }

    public LectureImpl(int numberOfLecture, String nameOfLecture) {
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
        this.literatures = new Literature[0];
    }

    public LectureImpl(int numberOfLecture, String nameOfLecture, Literature... lit) {
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
        this.literatures = lit;
    }

    public LectureImpl(int numberOfLecture, String nameOfLecture, Literature[] literatures, String lectorName) {
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
        this.literatures = literatures;
        this.lectorName = lectorName;
    }

    public LectureImpl(String nameOfLecture, int numberOfLecture, Literature[] literatures, Date lectureDate, String lectorName) {
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
        this.literatures = literatures;
        this.lectureDate = lectureDate;
        this.lectorName = lectorName;
    }

    //_______________________________________________________________________________________________________________//


    @Override
    public void setNameOfLecture(String nameOfLecture) {
        this.nameOfLecture = nameOfLecture;
    }

    @Override
    public String getNameOfLecture() {
        return nameOfLecture;
    }

    @Override
    public int getNumberOfLecture() {
        return this.numberOfLecture;
    }

    @Override
    public void setNumberOfLecture(int numberOfLecture) {
        this.numberOfLecture = numberOfLecture;
    }

    @Override
    public Literature[] getLiteratures() {
        return literatures;
    }

    @Override
    public void setLiteratures(Literature[] literatures) {
        this.literatures = literatures;
    }

    @Override
    public String toString() {
        return "Lecture №" + numberOfLecture +
                ". " + nameOfLecture;
    }

    public Date getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(Date lectureDate) {
        this.lectureDate = lectureDate;
    }

    public String getLectorName() {
        return lectorName;
    }

    public void setLectorName(String lectorName) {
        this.lectorName = lectorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureImpl lecture = (LectureImpl) o;
        return numberOfLecture == lecture.numberOfLecture &&
                nameOfLecture.equals(lecture.nameOfLecture) &&
                Arrays.equals(literatures, lecture.literatures);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nameOfLecture, numberOfLecture);
        result = 31 * result + Arrays.hashCode(literatures);
        return result;
    }
}
