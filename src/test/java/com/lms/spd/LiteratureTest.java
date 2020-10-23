package com.lms.spd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureTest {
    @Test
    void testToString() {
        Literature testLit = new Literature("Test");
        assertEquals(" Book= Test", testLit.toString());
    }

    @Test
    void testEquals() {
        Literature testLit = new Literature("Test");
        Literature testLit2 = new Literature("Test");
        Literature testLit3 = new Literature("Test2");
        assertTrue(testLit.equals(testLit2));
        assertFalse(testLit.equals(testLit3));
    }

    @Test
    void testHashCode() {
        Literature testLit = new Literature("Test");
        int hashCode = testLit.hashCode();
        assertEquals(hashCode, testLit.hashCode());
    }
}