package com.lms.spd.services.interfaces;

import com.lms.spd.exceptions.NullLectureException;
import com.lms.spd.models.interfaces.Lecture;

import java.io.IOException;
import java.util.List;

public interface LectureService {
    List<Lecture> getLectures();

    Lecture getSelectedLecture();

    void setLectures(List<Lecture> lectures) throws IOException;

    void setSelectedLecture(int selected) throws NullLectureException;

    void addLecture(Lecture lecture) throws IOException;

    void removeLectures(String[] lectureRemove) throws IOException;
}
