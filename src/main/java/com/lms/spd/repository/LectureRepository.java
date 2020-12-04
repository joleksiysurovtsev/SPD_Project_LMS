package com.lms.spd.repository;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.interfaces.LectureRepositoryInterface;
import com.lms.spd.repository.parsers.ParserLecturesJSON;

import java.util.List;

public class LectureRepository implements LectureRepositoryInterface {

    @Override
    public List<Lecture> getAll() {
        return ParserLecturesJSON.parseLecturesFromJSON();
    }

    @Override
    public void setAll(List<Lecture> lectures) {
        ParserLecturesJSON.parseLecturesInJSON(lectures);
    }
}
