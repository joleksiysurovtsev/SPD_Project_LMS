package com.lms.spd.repository;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectureRepositoryTest {
    @AfterEach
    @BeforeEach
    public void cleanUpFiles() {
        File targetFile = new File("src/test/resources/json/Lectures.json");
        File targetFile2 = new File("src/test/resources/json/Literatures.json");
        targetFile.delete();
        targetFile2.delete();
        ParserLecturesJSON.setURL("src/test/resources/json/");
        ParserLiteraturesJSON.seturl("src/test/resources/json/");
    }

    @Test
    @Order(28)
    void setAllTest() {
        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        LectureRepository lre = new LectureRepository();
        lre.setAll(testListL);
        assertEquals(testListL,lre.getAll());
    }
}