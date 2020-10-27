package com.lms.spd;

import com.lms.spd.models.LectureIModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lecture Test")
class LectureIModelTest {
    @Test
    void testToString() {
        LectureIModel testLecture = new LectureIModel(1, "Test");
        assertEquals("Lecture №1. Test",testLecture.toString());
    }

    @Test
    void testEquals() {
        LectureIModel testLecture = new LectureIModel(1, "Test");
        LectureIModel testLecture2 = new LectureIModel(1, "Test");
        LectureIModel testLecture3 = new LectureIModel( "Test2");
        assertEquals(testLecture2, testLecture);
        assertNotEquals(testLecture3, testLecture);
    }

    @Test
    void testHashCode() {
        LectureIModel testLecture = new LectureIModel(1, "Test");
        int hashCode = testLecture.hashCode();
        assertEquals(hashCode,testLecture.hashCode());
    }

}