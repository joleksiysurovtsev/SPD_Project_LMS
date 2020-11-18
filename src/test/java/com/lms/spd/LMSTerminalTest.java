package com.lms.spd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LMSTerminalTest {
    LMSTerminal terminal = new LMSTerminal();
    LiteratureValidator helper = new LiteratureValidator();

    @Test
    void stringToDeleteLecture() {
        String[] expected = {"1", "2"};
        assertArrayEquals(expected, helper.stringToDeleteLecture(("1 ,gfg, 2"),terminal.lectureServiceImpl.getLectures()));
    }

}