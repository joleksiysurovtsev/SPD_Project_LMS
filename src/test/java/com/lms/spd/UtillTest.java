package com.lms.spd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtillTest {

    @Test
    void getStringsNumberLecture() {
        int[] expected = {1, 2};
        assertArrayEquals(expected, Utill.getStringsNumberLecture("1fgghgg,hg2"));
    }

    @Test
    void getStringsNumberLecture2() {
        int[] expected = null;
        assertArrayEquals(expected, Utill.getStringsNumberLecture(""));
    }
}