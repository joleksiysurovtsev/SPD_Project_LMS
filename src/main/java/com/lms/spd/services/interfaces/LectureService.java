package com.lms.spd.services.interfaces;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.Date;

public interface LectureService {
    Lecture[] getLectures();

    void setLectures(Lecture[] lectures);

    Lecture getSelectedLecture();

    void setSelectedLecture(int selected);

    void addLecture(LectureType type, int numberOfLec, String nameOfLecture, Literature[] literatures, String lectorName, Date lectureDate);

    boolean removeLectures(int lectureRemove);

    String removeLectures(String lectureRemove);
}
