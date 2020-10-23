package com.lms.spd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LMSTerminalTest {
    LMSTerminal lmsTerminal = new LMSTerminal();
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
    void testCheckNumberLecture() {
        assertEquals(true, lmsTerminal.checkNumberLecture(1));
    }
}