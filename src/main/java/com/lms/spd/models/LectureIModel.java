package com.lms.spd.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class LectureIModel implements Lecture, Serializable {
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LectureIModel)) return false;
        LectureIModel that = (LectureIModel) o;
        if (id != that.id) return false;
        if (!Objects.equals(lectorName, that.lectorName)) return false;
        if (!Objects.equals(literatures, that.literatures)) return false;
        if (!Objects.equals(lectureDate, that.lectureDate)) return false;
        if (!Objects.equals(nameOfLecture, that.nameOfLecture)) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = getNameOfLecture().hashCode();
        result = 31 * result + getLiteratures().hashCode();
        result = 31 * result + getLectureDate().hashCode();
        result = 31 * result + getLectorName().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getId();
        return result;
    }
}
