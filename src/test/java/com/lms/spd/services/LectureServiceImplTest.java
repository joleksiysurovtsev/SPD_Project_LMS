package com.lms.spd.services;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
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
    @DisplayName("Remove one lecture from empty array: expected String 'Lectures: are missing.' ")
    void removeLecturesByStringTest() {
        assertEquals("Lectures: are missing.", lectureService.removeLectures("5,6"));
        assertEquals("Lectures:  1 successfully removed the rest are missing.", lectureService.removeLectures("1,5"));
        assertEquals("Lectures: are missing.", lectureService.removeLectures("5"));
    }

    @Test
    @DisplayName("Remove one lecture from empty array: expected false ")
    void removeLecturesByIntTest() {
        assertFalse(lectureService.removeLectures(5));
        assertTrue(lectureService.removeLectures(1));
    }
    @Test
    @DisplayName("Remove one lecture from empty array: expected false ")
    void removeLecturesByIntTest2() {
        assertTrue(lectureService.removeLectures(2));
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
        List<Lecture> expectedLectures = new ArrayList<>();
        {
            expectedLectures.add(new LectureIModel(1, "BufferedReader."));
            expectedLectures.add(new LectureIModel(2, "Writes text to."));
            expectedLectures.add(new LectureIModel(3, "Core Java API"));
            expectedLectures.add(new LectureIModel(LectureType.getValueByNumber(1), 4, "BufferedReader.", new ArrayList<>(), "Egorov", new Date()));
        }
        lectureService.addLecture(LectureType.getValueByNumber(1), 4, "BufferedReader.", new ArrayList<>(), "Egorov", new Date());
        assertEquals(expectedLectures, lectureService.getLectures());
    }

}