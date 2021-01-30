package com.lms.spd.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class LectureIModel implements Lecture,Serializable {

    @JsonProperty("NameOfLecture")
    private String nameOfLecture;

    @JsonProperty("List literatures")
    private List<Literature> literatures = new ArrayList<>();

    @JsonProperty("Date of lecture")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private Calendar lectureDate;

    @JsonProperty("Lector Name")
    private String lectorName;

    @JsonProperty("Lecture type")
    private LectureType type;

    @JsonProperty("Lecture ID")
    private int id;

    @JsonProperty("Duration Of The Lesson")
    private int durationOfTheLesson;
    //lecture constructor____________________________________________________________________________________________//


    public LectureIModel(String nameOfLecture, Calendar lectureDate, String lectorName, LectureType type, int durationOfTheLesson) {
        this.nameOfLecture = nameOfLecture;
        this.lectureDate = lectureDate;
        this.lectorName = lectorName;
        this.type = type;
        this.durationOfTheLesson = durationOfTheLesson;
    }

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

    public LectureIModel(String nameOfLecture, List<Literature> literatures, Calendar lectureDate, String lectorName, LectureType type, int durationOfTheLesson) {
        this.nameOfLecture = nameOfLecture;
        this.literatures = literatures;
        this.lectureDate = lectureDate;
        this.lectorName = lectorName;
        this.type = type;
        this.durationOfTheLesson = durationOfTheLesson;
    }

    public LectureIModel(LectureType type, String nameOfLecture, List<Literature> literatures, String lectorName, Calendar lectureDate, int id) {
        this.type = type;
        this.nameOfLecture = nameOfLecture;
        this.literatures = literatures;
        this.lectureDate = lectureDate;
        this.lectorName = lectorName;
        this.id = id;
    }

    public LectureIModel(String nameOfLecture, List<Literature> literatures, Calendar lectureDate, String lectorName, LectureType type, int id, int durationOfTheLesson) {
        this.nameOfLecture = nameOfLecture;
        this.literatures = literatures;
        this.lectureDate = lectureDate;
        this.lectorName = lectorName;
        this.type = type;
        this.id = id;
        this.durationOfTheLesson = durationOfTheLesson;
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
    public Calendar  getLectureDate() {
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

    public int getDurationOfTheLesson() {
        return durationOfTheLesson;
    }

    public void setDurationOfTheLesson(int durationOfTheLesson) {
        this.durationOfTheLesson = durationOfTheLesson;
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
                ", durationOfTheLesson=" + durationOfTheLesson +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureIModel that = (LectureIModel) o;
        return id == that.id &&
                durationOfTheLesson == that.durationOfTheLesson &&
                Objects.equals(nameOfLecture, that.nameOfLecture) &&
                Objects.equals(literatures, that.literatures) &&
                Objects.equals(lectureDate, that.lectureDate) &&
                Objects.equals(lectorName, that.lectorName) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfLecture, literatures, lectureDate, lectorName, type, id, durationOfTheLesson);
    }
}
