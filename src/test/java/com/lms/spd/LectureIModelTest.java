package com.lms.spd;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lecture Test")
class LectureIModelTest {
    @Test
    void testToString() {
        Lecture testLecture = new LectureIModel(1, "Test");
        assertEquals("Lecture №1. Test",testLecture.toString());
    }

    @Test
    void testEquals() {
        Lecture testLecture = new LectureIModel(1, "Test");
        Lecture testLecture2 = new LectureIModel(1, "Test");
        Lecture testLecture3 = new LectureIModel( 2,"Test2");
        assertEquals(testLecture2, testLecture);
        assertNotEquals(testLecture3, testLecture);
    }

    @Test
    void testHashCode() {
        Lecture testLecture = new LectureIModel(1, "Test");
        int hashCode = testLecture.hashCode();
        assertEquals(hashCode,testLecture.hashCode());
    }

}