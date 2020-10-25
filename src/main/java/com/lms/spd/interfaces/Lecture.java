package com.lms.spd.interfaces;

import com.lms.spd.Literature;

public interface Lecture {
    void setNameOfLecture(String nameOfLecture);

    String getNameOfLecture();

    int getNumberOfLecture();

    void setNumberOfLecture(int numberOfLecture);

    Literature[] getLiteratures();

    void setLiteratures(Literature[] literatures);
}
