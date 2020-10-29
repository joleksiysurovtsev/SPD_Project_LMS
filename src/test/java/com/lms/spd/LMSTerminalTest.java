package com.lms.spd;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.services.LectureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LMSTerminalTest {
    LMSTerminal lmsTerminal = new LMSTerminal();
    private LectureServiceImpl lectureServiceImpl;

    @BeforeEach
    void createLectureService() {
        lectureServiceImpl = new LectureServiceImpl();
        LectureIModel[] lectures = {
                new LectureIModel(1, "BufferedReader."),
                new LectureIModel(2, "Writes text to."),
                new LectureIModel(3, "Core Java API"),
        };
        lectureServiceImpl.setLectures(lectures);
    }

    @Test
    void testCheckNumberLecture() {
        assertTrue(lmsTerminal.checkNumberLecture(1));
    }
}