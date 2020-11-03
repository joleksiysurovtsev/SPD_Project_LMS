package com.lms.spd;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LMSTerminalTest {

    LMSTerminal lmsTerm = new LMSTerminal();
    private LectureServiceImpl lectureServic;

    @Test
    void testCheckNumberLecture() {
        Lecture lecture = new LectureIModel(1, "BufferedReader");
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(lecture);
        assertTrue(lmsTerm.checkNumberLecture(1));
    }
}