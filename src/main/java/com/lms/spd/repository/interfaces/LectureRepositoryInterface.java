package com.lms.spd.repository.interfaces;

import com.lms.spd.models.interfaces.Lecture;

import java.io.IOException;

public interface LectureRepositoryInterface  {

    void addLecture(Lecture lecture) throws IOException;

    void removeLecture(int id) throws IOException;

}
