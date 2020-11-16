package com.lms.spd.models;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.Calendar;
import java.util.List;

public class LectureIModel implements Lecture {
    private String nameOfLecture;
    private int numberOfLecture;
    private List<Literature> literatures;
    private Calendar lectureDate;
    private String lectorName;
    private LectureType type;
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        if (getNumberOfLecture() != that.getNumberOfLecture()) return false;
        if (getId() != that.getId()) return false;
        if (!getNameOfLecture().equals(that.getNameOfLecture())) return false;
        if (!getLiteratures().equals(that.getLiteratures())) return false;
        if (!getLectureDate().equals(that.getLectureDate())) return false;
        if (!getLectorName().equals(that.getLectorName())) return false;
        return getType() == that.getType();
    }

    @Override
    public int hashCode() {
        int result = getNameOfLecture().hashCode();
        result = 31 * result + getNumberOfLecture();
        result = 31 * result + getLiteratures().hashCode();
        result = 31 * result + getLectureDate().hashCode();
        result = 31 * result + getLectorName().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + (int) (getId() ^ (getId() >>> 32));
        return result;
    }
}
