package com.lms.spd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LectureServiceTest {
    private LectureService lectureService;

    @BeforeEach
    void createLectureService() {
        lectureService = new LectureService();

        Lecture[] lectures = {
                new Lecture(1, "BufferedReader."),
                new Lecture(2, "Writes text to."),
                new Lecture(3, "Core Java API"),
        };

        lectureService.setLectures(lectures);
    }

    @Test
    void removeLecturesByInt() {
        Lecture[] lecturesAssert = {
                new Lecture(1, "BufferedReader."),
                new Lecture(2, "Writes text to."),
        };

        boolean successful = lectureService.removeLectures(3);
        assertArrayEquals(lecturesAssert, lectureService.getLectures());
        assertTrue(successful);
    }

    @Test
    void removeLecturesByString() {
        Lecture[] lecturesAssert = {new Lecture(1, "BufferedReader.")};

        lectureService.removeLectures("2, 3");
        assertArrayEquals(lecturesAssert, lectureService.getLectures());
    }


}