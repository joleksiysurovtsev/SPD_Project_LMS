package com.lms.spd.enums;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LectureTypeTest {

    @Test
    @Order(1)
    void getValueByNumberTest1() {
        assertEquals(LectureType.JAVA_CORE, LectureType.getValueByNumber(1));
    }

    @Test
    @Order(2)
    void getValueByNumberTest2() {
        assertEquals(LectureType.JAVA_CONCURRENCY, LectureType.getValueByNumber(2));
    }

    @Test
    @Order(3)
    void getValueByNumberTest3() {
        assertNull(LectureType.getValueByNumber(10));
    }
}