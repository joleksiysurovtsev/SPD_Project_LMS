package com.lms.spd.repository;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectureRepositoryTest {

    @Test
    void setAllTest() {
        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        LectureRepository lre = new LectureRepository();
        lre.setAll(testListL);

        assertEquals(testListL,lre.getAll());
    }
}