package com.lms.spd.models.interfaces;

import com.lms.spd.models.AbstractLiterature;

public interface Lecture {

    void setNameOfLecture(String nameOfLecture);

    String getNameOfLecture();

    int getNumberOfLecture();

    void setNumberOfLecture(int numberOfLecture);

    AbstractLiterature[] getLiteratures();

    void setLiteratures(AbstractLiterature[] literatures);
}
