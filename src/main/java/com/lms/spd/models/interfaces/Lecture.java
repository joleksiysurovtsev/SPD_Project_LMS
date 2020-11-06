package com.lms.spd.models.interfaces;

import com.lms.spd.enums.LectureType;

import java.util.Calendar;
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

    Calendar getLectureDate();

    void setLectureDate(Calendar lectureDate);

    LectureType getType();

    void setType(LectureType type);
}
