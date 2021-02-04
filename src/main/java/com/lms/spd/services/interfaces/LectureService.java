package com.lms.spd.services.interfaces;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;

import java.util.List;
import java.util.Map;

public interface LectureService {
    List<Lecture> getLectures();

    Lecture getSelectedLecture();

    void setLectures(List<Lecture> lectures);

    void setSelectedLecture(int selected);

    void addLecture(Lecture lecture);

    void removeLectures(int[] lectureRemove);

    Map<LectureType, List<Lecture>> getMapSortedByType();
}
