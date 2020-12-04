package com.lms.spd.services.interfaces;

import com.lms.spd.models.interfaces.Lecture;

import java.util.List;

public interface LectureService {
    List<Lecture> getLectures();

    Lecture getSelectedLecture();

    void setLectures(List<Lecture> lectures) ;

    void setSelectedLecture(int selected) ;

    void addLecture(Lecture lecture) ;

    void removeLectures(int[] lectureRemove) ;
}
