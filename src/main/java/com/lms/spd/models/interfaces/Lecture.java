package com.lms.spd.models.interfaces;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lms.spd.enums.LectureType;

import java.util.Calendar;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@ class")
public interface Lecture {

    void setNameOfLecture(String nameOfLecture);

    String getNameOfLecture();

    int getNumberOfLecture();

    void setNumberOfLecture(int numberOfLecture);

    List<Literature> getLiteratures();

    void setLiteratures(List<Literature> literatures);

    String getLectorName();

    void setLectorName(String nameOfLecture);

    Calendar getLectureDate();

    void setLectureDate(Calendar lectureDate);

    LectureType getType();

    void setType(LectureType type);

    public int getId();

    public void setId(int id);
}
