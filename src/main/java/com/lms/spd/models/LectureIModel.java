package com.lms.spd.models;

import com.alibaba.fastjson.annotation.JSONField;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.Calendar;
import java.util.List;

public class LectureIModel implements Lecture {
    @JSONField(name = "NAME OF LECTURE")
    private String nameOfLecture;

    @JSONField(name = "NUMBER OF LECTURE")
    private int numberOfLecture;

    @JSONField(name = "LIST LITERATURES")
    private List<Literature> literatures;

    @JSONField(name = "LECTURE DATE", format="dd/MM/yyyy")
    private Calendar lectureDate;

    @JSONField(name = "LECTOR NAME")
    private String lectorName;

    @JSONField(name = "LECTURE TYPE")
    private LectureType type;

    @JSONField(name = "LECTURE ID")
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

        if (numberOfLecture != that.numberOfLecture) return false;
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
        int result = getNameOfLecture().hashCode();
        result = 31 * result + getNumberOfLecture();
        result = 31 * result + getLiteratures().hashCode();
        result = 31 * result + getLectureDate().hashCode();
        result = 31 * result + getLectorName().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getId();
        return result;
    }
}
