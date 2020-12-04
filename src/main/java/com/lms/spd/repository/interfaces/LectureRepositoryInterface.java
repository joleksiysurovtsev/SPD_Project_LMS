package com.lms.spd.repository.interfaces;

import com.lms.spd.models.interfaces.Lecture;

import java.io.IOException;
import java.util.List;

public interface LectureRepositoryInterface  {

    List<Lecture> getAll();

    void setAll(List<Lecture> lectures) throws IOException;
}
