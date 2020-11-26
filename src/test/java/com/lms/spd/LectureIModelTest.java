package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lecture Test")
class LectureIModelTest {
    @Test
    void testToString() {
        Lecture testLecture = new LectureIModel( "Test");
        assertEquals("LectureIModel{nameOfLecture='Test', numberOfLecture=1, literatures=null, lectureDate=null, lectorName='null', type=null}",testLecture.toString());
    }

    @Test
    void testEquals() {
        Lecture testLecture = new LectureIModel(LectureType.JAVA_CORE,  "\"Intro. Java Basics\"", new ArrayList<>(), "Vova Shevchenko", new GregorianCalendar(2020, 9, 5),50);
        Lecture testLecture2 = new LectureIModel(LectureType.JAVA_CORE, "\"Intro. Java Basics\"", new ArrayList<>(), "Vova Shevchenko", new GregorianCalendar(2020, 9, 5),50);
        Lecture testLecture3 = new LectureIModel(LectureType.JAVA_CORE, "\"Intro. Java Basics\"", new ArrayList<>(), "Vova Shevchenko", new GregorianCalendar(2020, 9, 5),50);
        assertEquals(testLecture2, testLecture);
        assertNotEquals(testLecture3, testLecture);
    }

    @Test
    void testHashCode() {
        Lecture testLecture = new LectureIModel(LectureType.JAVA_CORE, "\"Intro. Java Basics\"", new ArrayList<>(), "Vova Shevchenko", new GregorianCalendar(2020, 9, 5),50);

        int hashCode = testLecture.hashCode();
        assertEquals(hashCode,testLecture.hashCode());
    }

}