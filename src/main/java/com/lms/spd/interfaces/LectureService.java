package com.lms.spd.interfaces;

import com.lms.spd.LectureImpl;
import com.lms.spd.Literature;

import java.util.Date;

public interface LectureService {
    LectureImpl[] getLectures();

    void setLectures(LectureImpl[] lectures);

    LectureImpl getSelectedLecture();

    void setSelectedLecture(int selected);

    void addLecture(int numberOfLec, String nameOfLecture, Literature[] literatures, String lectorName, Date lectureDate);

    boolean removeLectures(int lectureRemove);

    String removeLectures(String lectureRemove);
}
