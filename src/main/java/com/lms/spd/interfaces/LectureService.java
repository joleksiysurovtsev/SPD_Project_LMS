package com.lms.spd.interfaces;

import com.lms.spd.LectureImpl;
import com.lms.spd.Literature;

public interface LectureService {
    LectureImpl[] getLectures();

    void setLectures(LectureImpl[] lectures);

    LectureImpl getSelectedLecture();

    void setSelectedLecture(int selected);

    void addLecture(String name, Literature[] literature);

    void addLecturePlusNumber(int number, String name, Literature[] literature);

    boolean removeLectures(int lectureRemove);

    String removeLectures(String lectureRemove);
}
