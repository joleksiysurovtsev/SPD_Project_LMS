package com.lms.spd.repository.interfaces;

import com.lms.spd.models.interfaces.Lecture;

public interface LectureRepositoryInterface  {

    void addLecture(Lecture lecture);

    void removeLecture(int id);

}
