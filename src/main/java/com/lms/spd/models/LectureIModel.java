package com.lms.spd.models;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class LectureIModel implements Lecture {
    private String nameOfLecture;
    private int numberOfLecture;
    private AbstractLiterature[] literatures;
    private Date lectureDate;
    private String lectorName;
    private LectureType type;

    //lecture constructor____________________________________________________________________________________________//

    public LectureIModel(String nameOfLecture) {
        this.nameOfLecture = nameOfLecture;
        this.literatures = new AbstractLiterature[0];
    }

    public LectureIModel(int numberOfLecture, String nameOfLecture, AbstractLiterature... lit) {
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
        this.literatures = lit;
    }

    public LectureIModel(int numberOfLecture, String nameOfLecture, AbstractLiterature[] literatures, String lectorName, Date lectureDate) {
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
    public AbstractLiterature[] getLiteratures() {
        return literatures;
    }

    @Override
    public void setLiteratures(AbstractLiterature[] literatures) {
        this.literatures = literatures;
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


    public LectureType getType() {
        return type;
    }

    public void setType(LectureType type) {
        this.type = type;
    }





    @Override
    public String toString() {
        return "Lecture №" + numberOfLecture +
                ". " + nameOfLecture;
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
