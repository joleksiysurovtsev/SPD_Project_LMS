package com.lms.spd.models.interfaces;

import com.lms.spd.enums.LectureType;

import java.util.ArrayList;
import java.util.Date;

public interface Lecture {

    void setNameOfLecture(String nameOfLecture);

    String getNameOfLecture();

    int getNumberOfLecture();

    void setNumberOfLecture(int numberOfLecture);

    ArrayList<Literature> getLiteratures();

    void setLiteratures(ArrayList<Literature> literatures);

    String getLectorName();

    void setLectorName(String nameOfLecture);

    Date getLectureDate();

    void setLectureDate(Date lectureDate);

    LectureType getType();

    void setType(LectureType type);
}
