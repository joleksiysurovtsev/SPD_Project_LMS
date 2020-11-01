package com.lms.spd.models.interfaces;

import com.lms.spd.enums.LectureType;

import java.util.Date;
import java.util.List;

public interface Lecture {

    void setNameOfLecture(String nameOfLecture);

    String getNameOfLecture();

    int getNumberOfLecture();

    void setNumberOfLecture(int numberOfLecture);

    List<Literature> getLiteratures();

    void setLiteratures(List<Literature> literatures);

    String getLectorName();

    void setLectorName(String nameOfLecture);

    Date getLectureDate();

    void setLectureDate(Date lectureDate);

    LectureType getType();

    void setType(LectureType type);
}
