package com.lms.spd.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.io.Serializable;

import java.util.Calendar;
import java.util.List;


public class LectureIModel implements Lecture,Serializable {

    @JsonProperty("NameOfLecture")
    private String nameOfLecture;

    @JsonProperty("List literatures")
    private List<Literature> literatures;

    @JsonProperty("Date of lecture")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Calendar lectureDate;

    @JsonProperty("Lector Name")
    private String lectorName;

    @JsonProperty("Lecture type")
    private LectureType type;

    @JsonProperty("Lecture ID")
    private int id;
    //lecture constructor____________________________________________________________________________________________//


    public LectureIModel() {}

    public LectureIModel(String nameOfLecture) {
        this.nameOfLecture = nameOfLecture;
    }

    public LectureIModel(LectureType type, String nameOfLecture, List<Literature> literatures, String lectorName, Calendar lectureDate) {
        this.type = type;
        this.nameOfLecture = nameOfLecture;
        this.literatures = literatures;
        this.lectureDate = lectureDate;
        this.lectorName = lectorName;
    }

    public LectureIModel(LectureType type, String nameOfLecture, List<Literature> literatures, String lectorName, Calendar lectureDate, int id) {
        this.type = type;
        this.nameOfLecture = nameOfLecture;

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
                ", literatures=" + literatures +
                ", lectureDate=" + lectureDate +
                ", lectorName='" + lectorName + '\'' +
                ", type=" + type +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LectureIModel)) return false;

        LectureIModel that = (LectureIModel) o;

        if (id != that.id) return false;
        if (nameOfLecture != null ? !nameOfLecture.equals(that.nameOfLecture) : that.nameOfLecture != null)
            return false;
        if (literatures != null ? !literatures.equals(that.literatures) : that.literatures != null) return false;
        if (lectureDate != null ? !lectureDate.equals(that.lectureDate) : that.lectureDate != null) return false;
        if (lectorName != null ? !lectorName.equals(that.lectorName) : that.lectorName != null) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = nameOfLecture != null ? nameOfLecture.hashCode() : 0;
        result = 31 * result + (literatures != null ? literatures.hashCode() : 0);
        result = 31 * result + (lectureDate != null ? lectureDate.hashCode() : 0);
        result = 31 * result + (lectorName != null ? lectorName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
