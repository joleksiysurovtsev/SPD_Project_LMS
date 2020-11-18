package com.lms.spd;

import com.lms.spd.services.LectureServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LMSTerminalTest {
    LectureValidator lectureValidator = new LectureValidator();
    LectureServiceImpl lectureServiceImpl = new LectureServiceImpl();

    @Test
    void stringToDeleteLecture() {
        String[] expected = {"1", "2"};
        assertArrayEquals(expected, lectureValidator.stringToDeleteLecture(("1 ,gfg, 2"), lectureServiceImpl.getLectures()));
    }

}