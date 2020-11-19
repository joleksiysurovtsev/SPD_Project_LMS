package com.lms.spd.services.interfaces;

import com.lms.spd.exceptions.NullLectureException;
import com.lms.spd.models.interfaces.Lecture;
import java.util.List;

public interface LectureService {
    List<Lecture> getLectures();

    Lecture getSelectedLecture();

    void setLectures(List<Lecture> lectures);

    void setSelectedLecture(int selected) throws NullLectureException;

    void addLecture(Lecture lecture);

    void removeLectures(String[] lectureRemove);
}
