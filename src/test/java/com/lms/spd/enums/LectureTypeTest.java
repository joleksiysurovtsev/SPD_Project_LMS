package com.lms.spd.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LectureTypeTest {

    @Test
    void getValueByNumberTest1() {
        assertEquals(LectureType.JAVA_CORE,LectureType.getValueByNumber(1));
    }

    @Test
    void getValueByNumberTest2() {
        assertEquals(LectureType.JAVA_CONCURRENCY,LectureType.getValueByNumber(2));
    }

    @Test
    void getValueByNumberTest3() {
        assertNull(LectureType.getValueByNumber(10));
    }
}