package com.lms.spd.models;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class LectureIModel implements Lecture {
    private String nameOfLecture;
    private int numberOfLecture;
    private List<Literature> literatures;
    private Calendar lectureDate;
    private String lectorName;
    private LectureType type;
    private long id;
    //lecture constructor____________________________________________________________________________________________//

    public LectureIModel(String nameOfLecture) {
        this.nameOfLecture = nameOfLecture;
    }

    public LectureIModel(int numberOfLecture, String nameOfLecture) {
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
    }

    public LectureIModel(LectureType type, int numberOfLecture, String nameOfLecture, List<Literature> literatures, String lectorName, Calendar lectureDate) {
        this.type = type;
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
        this.literatures = literatures;
        this.lectureDate = lectureDate;
        this.lectorName = lectorName;
    }

    public LectureIModel(LectureType type, int numberOfLecture, String nameOfLecture, List<Literature> literatures, String lectorName, Calendar lectureDate, int id) {
        this.type = type;
        this.nameOfLecture = nameOfLecture;
        this.numberOfLecture = numberOfLecture;
        this.literatures = literatures;
        this.lectureDate = lectureDate;
        this.lectorName = lectorName;
        this.id = id;
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
    public void setLiteratures(List<Literature> literatures) {
        this.literatures = literatures;
    }

    @Override
    public List<Literature> getLiteratures() {
        return literatures;
    }

    @Override
    public void setLectureDate(Calendar lectureDate) {
        this.lectureDate = lectureDate;
    }

    @Override
    public Calendar getLectureDate() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    @Override
    public String toString() {
        return "LectureIModel{" +
                "nameOfLecture='" + nameOfLecture + '\'' +
                ", numberOfLecture=" + numberOfLecture +
                ", literatures=" + literatures +
                ", lectureDate=" + lectureDate +
                ", lectorName='" + lectorName + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LectureIModel)) return false;
        LectureIModel that = (LectureIModel) o;
        return getNumberOfLecture() == that.getNumberOfLecture() &&
                Objects.equals(getNameOfLecture(), that.getNameOfLecture()) &&
                Objects.equals(getLiteratures(), that.getLiteratures()) &&
                Objects.equals(getLectureDate(), that.getLectureDate()) &&
                Objects.equals(getLectorName(), that.getLectorName()) &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameOfLecture(), getNumberOfLecture(), getLiteratures(), getLectureDate(), getLectorName(), getType());
    }
}
