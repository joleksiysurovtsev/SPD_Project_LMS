package com.lms.spd.repository.parsers;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserLecturesJSONTest {

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
    @Order(21)
    void parseLecturesJSON() {

        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        ParserLecturesJSON.parseLecturesInJSON(testListL);
        List<Lecture> resultListL = ParserLecturesJSON.parseLecturesFromJSON();
        //what we recorded what we got
        assertEquals(resultListL, testListL);
    }


    @Test
    @Order(22)
    void parseLecturesJSON2() {
        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        ParserLecturesJSON.parseLecturesInJSON(testListL);

        assertEquals(testListL, ParserLecturesJSON.parseLecturesFromJSON());
    }


    @Test
    @Order(23)
    @DisplayName("Checking if a blank sheet is returned")
    void parseLecturesJSON3() {
        List<Lecture> resultListL = ParserLecturesJSON.parseLecturesFromJSON();
        List<Lecture> testListL = new ArrayList<>();
        assertEquals(testListL, resultListL);
    }

    @Test
    @Order(24)
    @DisplayName("check if the file is lit")
    void parseLecturesJSON4() {

        String url = "src/test/resources/json/";
        assertEquals(url, ParserLecturesJSON.getDirName());
    }
}