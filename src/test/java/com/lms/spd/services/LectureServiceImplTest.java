package com.lms.spd.services;

import com.lms.spd.LecturesCash;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectureServiceImplTest {

    private LectureServiceImpl lectureService;


    @BeforeEach
    void createLectureService() {
        lectureService = new LectureServiceImpl();
        ArrayList<Lecture> lectures = new ArrayList<>();
        lectures.add(new LectureIModel(1, "BufferedReader."));
        lectures.add(new LectureIModel(2, "Writes text to."));
        lectures.add(new LectureIModel(3, "Core Java API"));

        lectureService.setLectures(lectures);
    }

    @Test
    @DisplayName("setSelectedLecture method selects by position in the array")
    void getSelectedLecture() {
        lectureService.setSelectedLecture(0);
        Lecture expectedLecture = new LectureIModel(1, "BufferedReader.");
        assertEquals(expectedLecture, lectureService.getSelectedLecture());
    }

    @Test
    @DisplayName("Add new lecture to array "
            + "expected: one more lecture will be added to the end of the lecture array")
    void addLecture() {
        LectureServiceImpl lectureService = new LectureServiceImpl();
        List<Lecture> tested = lectureService.getLectures();
        {
            tested.add(new LectureIModel(LectureType.getValueByNumber(1), 1, "BufferedReader.", new ArrayList<>(), "Egorov", new GregorianCalendar(2022, 06, 15)));
        }
        lectureService.addLecture(new LectureIModel(LectureType.getValueByNumber(1), 1, "BufferedReader.", new ArrayList<>(), "Egorov", new GregorianCalendar(2022, 06, 15)));
        assertEquals(tested, lectureService.getLectures());
    }

    @Test
    @DisplayName("Adding a lecture to the middle of the array")
    void addLecture2() {
        LectureServiceImpl lectureService = new LectureServiceImpl();
        List<Lecture> expectedLectures = lectureService.getLectures();
        {
            expectedLectures.add(new LectureIModel(LectureType.getValueByNumber(1), 3, "BufferedReader.", new ArrayList<>(), "Egorov", new GregorianCalendar(2020, 10, 6)));
        }

        lectureService.addLecture(new LectureIModel(LectureType.getValueByNumber(1), 3, "BufferedReader.", new ArrayList<>(), "Egorov", new GregorianCalendar(2020, 10, 6)));
        assertEquals(expectedLectures, lectureService.getLectures());
    }

    @Test
    @DisplayName("Adding a lecture to the middle of the array")
    void addLecture3() {
        LectureServiceImpl lectureService = new LectureServiceImpl();
        ArrayList<Lecture> lectures = new ArrayList<>();
        lectureService.setLectures(lectures);

        List<Lecture> expectedLectures = lectureService.getLectures();
        {
            expectedLectures.add(new LectureIModel(LectureType.getValueByNumber(1), 3, "BufferedReader.", new ArrayList<>(), "Egorov", new GregorianCalendar(2020, 10, 6)));
        }

        lectureService.addLecture(new LectureIModel(LectureType.getValueByNumber(1), 3, "BufferedReader.", new ArrayList<>(), "Egorov", new GregorianCalendar(2020, 10, 6)));
        assertEquals(expectedLectures, lectureService.getLectures());
    }





}