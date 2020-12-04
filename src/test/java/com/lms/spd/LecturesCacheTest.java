package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LecturesCacheTest {
    @AfterEach
    @BeforeEach
    public void cleanUpFiles() {
        File targetFile = new File("src/test/resources/json/Lectures.json");
        File targetFile2 = new File("src/test/resources/json/Literatures.json");
        targetFile.delete();
        targetFile2.delete();
        ParserLecturesJSON.seturl("src/test/resources/json/");
        ParserLiteraturesJSON.seturl("src/test/resources/json/");
    }

    @Test
    @Order(41)
    @Description("Testing of setting a certain date for the cache")
    void setCurrentDate() {
        LectureIModel lectureIModel = new LectureIModel(LectureType.CAREER,"test",new ArrayList<>(),"test",new GregorianCalendar(2020,10,05),1);
        LectureIModel lectureIModel2 = new LectureIModel(LectureType.COMMON,"test",new ArrayList<>(),"test",new GregorianCalendar(2020,10,06),2);
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        testListL.add(lectureIModel2);

        ParserLecturesJSON.parseLecturesInJSON(testListL);
        LecturesCache cache = new LecturesCache();
        cache.setCurrentDate(new GregorianCalendar(2020,10,05));

        assertEquals(new GregorianCalendar(2020,10,05), cache.getCurrentDate());
    }

    @Test
    @Order(42)
    @Description("Method test that should return a list of lectures on a given date")
    void returnListOfLecturesOnAGivenDate() {
        LectureIModel lectureIModel = new LectureIModel(LectureType.CAREER,"test",new ArrayList<>(),"test",new GregorianCalendar(2020,10,05),1);
        LectureIModel lectureIModel2 = new LectureIModel(LectureType.COMMON,"test",new ArrayList<>(),"test",new GregorianCalendar(2020,10,06),2);
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        testListL.add(lectureIModel2);

        ParserLecturesJSON.parseLecturesInJSON(testListL);
        LecturesCache cache = new LecturesCache();
        cache.setCurrentDate(new GregorianCalendar(2020,10,06));

        List<Lecture> assertedTestListL = new ArrayList<>();
        assertedTestListL.add(lectureIModel2);

        assertEquals(assertedTestListL, cache.returnList());

    }

    @Test
    @Order(43)
    @Description("Method2 test that should return a list of lectures on a given date")
    void testCaseTwoReturnAListOfLecturesOnAGivenDate() {
        LectureIModel lectureIModel = new LectureIModel(LectureType.CAREER,"test",new ArrayList<>(),"test",new GregorianCalendar(2020,10,05),1);
        LectureIModel lectureIModel2 = new LectureIModel(LectureType.COMMON,"test",new ArrayList<>(),"test",new GregorianCalendar(2020,10,06),2);
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        testListL.add(lectureIModel2);

        ParserLecturesJSON.parseLecturesInJSON(testListL);
        LecturesCache cache = new LecturesCache();
        cache.setCurrentDate(new GregorianCalendar(2020,10,05));

        List<Lecture> assertedTestListL = new ArrayList<>();
        assertedTestListL.add(lectureIModel);

        assertEquals(assertedTestListL, cache.returnList());

    }


    @Test
    @Order(44)
    @Description("Method2 test that should return a list of lectures on a given date")
    void testCaseThreeReturnAListOfLecturesOnAGivenDate() {
        LectureIModel lectureIModel = new LectureIModel(LectureType.CAREER,"test",new ArrayList<>(),"test",new GregorianCalendar(2020,10,05),1);
        LectureIModel lectureIModel2 = new LectureIModel(LectureType.COMMON,"test3",new ArrayList<>(),"test",new GregorianCalendar(2020,10,06),2);
        LectureIModel lectureIModel3 = new LectureIModel(LectureType.JAVA_CORE,"test4",new ArrayList<>(),"test",new GregorianCalendar(2020,10,06),3);
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        testListL.add(lectureIModel2);
        testListL.add(lectureIModel3);

        ParserLecturesJSON.parseLecturesInJSON(testListL);
        LecturesCache cache = new LecturesCache();

        cache.setCurrentDate(new GregorianCalendar(2020,10,05));

        List<Lecture> assertedTestListL = new ArrayList<>();
        assertedTestListL.add(lectureIModel);

        assertEquals(assertedTestListL, cache.returnList());

    }
}