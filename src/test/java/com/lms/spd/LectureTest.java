package com.lms.spd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lecture Test")
class LectureTest {

    @Test
    void setNameOfLectures() {
        Lecture testLecture = new Lecture(1, "Test");
        testLecture.setNameOfLectures("New name");
        Lecture assertLectures = new Lecture(1, "New name");
        assertEquals(assertLectures,testLecture);
    }

    @Test
    void getNameOfLectures() {
        Lecture testLecture = new Lecture(1, "Test");
        assertEquals("Test",testLecture.getNameOfLectures());
    }

    @Test
    void testToString() {
        Lecture testLecture = new Lecture(1, "Test");
        assertEquals("Lecture №1. Test",testLecture.toString());
    }

    @Test
    void testEquals() {
        Lecture testLecture = new Lecture(1, "Test");
        Lecture testLecture2 = new Lecture(1, "Test");
        Lecture testLecture3 = new Lecture( "Test2");
        assertTrue(testLecture.equals(testLecture2));
        assertFalse(testLecture.equals(testLecture3));
    }

    @Test
    void testHashCode() {
        Lecture testLecture = new Lecture(1, "Test");
        int hashCode = testLecture.hashCode();
        assertEquals(hashCode,testLecture.hashCode());
    }

}