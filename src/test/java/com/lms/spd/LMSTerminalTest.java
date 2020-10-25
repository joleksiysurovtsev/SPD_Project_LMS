package com.lms.spd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LMSTerminalTest {
    LMSTerminal lmsTerminal = new LMSTerminal();
    private LectureServiceImpl lectureServiceImpl;

    @BeforeEach
    void createLectureService() {
        lectureServiceImpl = new LectureServiceImpl();
        LectureImpl[] lectures = {
                new LectureImpl(1, "BufferedReader."),
                new LectureImpl(2, "Writes text to."),
                new LectureImpl(3, "Core Java API"),
        };
        lectureServiceImpl.setLectures(lectures);
    }

    @Test
    void testCheckNumberLecture() {
        assertEquals(true, lmsTerminal.checkNumberLecture(1));
    }
}