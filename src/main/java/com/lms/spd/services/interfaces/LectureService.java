package com.lms.spd.services.interfaces;

import com.lms.spd.models.interfaces.Lecture;
import java.util.List;

public interface LectureService {
    List<Lecture> getLectures();

    void setLectures(List<Lecture> lectures);

    Lecture getSelectedLecture();

    void setSelectedLecture(int selected);

    void addLecture(Lecture lecture);

    boolean removeLectures(int lectureRemove);

    void removeLectures(String[] lectureRemove);
}
