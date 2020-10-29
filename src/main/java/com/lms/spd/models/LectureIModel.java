package com.lms.spd.models;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class LectureIModel implements Lecture {
    private String nameOfLecture;
    private int numberOfLecture;
    private Literature[] literatures;
    private Date lectureDate;
    private String lectorName;
    private LectureType type;

    //lecture constructor____________________________________________________________________________________________//

    public LectureIModel(String nameOfLecture) {
        this.nameOfLecture = nameOfLecture;
        this.literatures = new Literature[0];
    }

    public LectureIModel(int numberOfLecture, String nameOfLecture, Literature... lit) {
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
        this.literatures = lit;
    }

    public LectureIModel(int numberOfLecture, String nameOfLecture, Literature[] literatures, String lectorName, Date lectureDate) {
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
        this.literatures = literatures;
        this.lectureDate = lectureDate;
        this.lectorName = lectorName;
    }

    public LectureIModel(LectureType type, int numberOfLecture, String nameOfLecture, Literature[] literatures, String lectorName, Date lectureDate) {
        this.type = type;
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
    public void setNumberOfLecture(int numberOfLecture) {
        this.numberOfLecture = numberOfLecture;
    }

    @Override
    public int getNumberOfLecture() {
        return this.numberOfLecture;
    }

    @Override
    public void setLiteratures(Literature[] literatures) {
        this.literatures = literatures;
    }

    @Override
    public Literature[] getLiteratures() {
        return literatures;
    }

    @Override
    public void setLectureDate(Date lectureDate) {
        this.lectureDate = lectureDate;
    }

    @Override
    public Date getLectureDate() {
        return lectureDate;
    }

    @Override
    public void setLectorName(String lectorName) {
        this.lectorName = lectorName;
    }

    @Override
    public String getLectorName() {
        return lectorName;
    }

    @Override
    public LectureType getType() {
        return type;
    }
    @Override
    public void setType(LectureType type) {
        this.type = type;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    @Override
    public String toString() {
        return "Lecture №" + numberOfLecture + ". " + nameOfLecture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureIModel lecture = (LectureIModel) o;
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
