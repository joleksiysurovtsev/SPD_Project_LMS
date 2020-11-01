package com.lms.spd.services.interfaces;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface LectureService {
    List<Lecture> getLectures();

    void setLectures(List<Lecture> lectures);

    Lecture getSelectedLecture();

    void setSelectedLecture(int selected);

    void addLecture(LectureType type, int numberOfLec, String nameOfLecture, List<Literature> literatures, String lectorName, Date lectureDate);

    boolean removeLectures(int lectureRemove);

    String removeLectures(String lectureRemove);
}
