package com.lms.spd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lecture Test")
class LectureImplTest {
    @Test
    void testToString() {
        LectureImpl testLecture = new LectureImpl(1, "Test");
        assertEquals("Lecture №1. Test",testLecture.toString());
    }

    @Test
    void testEquals() {
        LectureImpl testLecture = new LectureImpl(1, "Test");
        LectureImpl testLecture2 = new LectureImpl(1, "Test");
        LectureImpl testLecture3 = new LectureImpl( "Test2");
        assertTrue(testLecture.equals(testLecture2));
        assertFalse(testLecture.equals(testLecture3));
    }

    @Test
    void testHashCode() {
        LectureImpl testLecture = new LectureImpl(1, "Test");
        int hashCode = testLecture.hashCode();
        assertEquals(hashCode,testLecture.hashCode());
    }

}