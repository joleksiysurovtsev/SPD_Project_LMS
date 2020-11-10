package com.lms.spd;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LMSTerminalTest {
    LMSTerminal terminal = new LMSTerminal();

    @Test
    void stringToDeleteLecture() {
        String[] expected = {"1", "2"};
        assertArrayEquals(expected, terminal.stringToDeleteLecture("1 ,gfg, 2"));
    }

}