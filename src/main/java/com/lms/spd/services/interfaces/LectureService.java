package com.lms.spd.services.interfaces;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.AbstractLiterature;

import java.util.Date;

public interface LectureService {
    LectureIModel[] getLectures();

    void setLectures(LectureIModel[] lectures);

    LectureIModel getSelectedLecture();

    void setSelectedLecture(int selected);

    void addLecture(LectureType type, int numberOfLec, String nameOfLecture, AbstractLiterature[] literatures, String lectorName, Date lectureDate);

    boolean removeLectures(int lectureRemove);

    String removeLectures(String lectureRemove);
}
