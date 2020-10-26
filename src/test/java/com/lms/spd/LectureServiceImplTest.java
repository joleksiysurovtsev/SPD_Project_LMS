package com.lms.spd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LectureServiceImplTest {

    private LectureServiceImpl lectureService;

    @BeforeEach
    void createLectureService() {
        lectureService = new LectureServiceImpl();
        LectureImpl[] lectures = {
                new LectureImpl(1, "BufferedReader."),
                new LectureImpl(2, "Writes text to."),
                new LectureImpl(3, "Core Java API"),
        };
        lectureService.setLectures(lectures);
    }


    @Test
    @DisplayName("Remove one lecture from empty array: expected String 'Lectures: are missing.' ")
    void removeLecturesByStringTest() {
        assertEquals("Lectures: are missing.", lectureService.removeLectures("5,6"));
        assertEquals("Lectures:  1 successfully removed the rest are missing.", lectureService.removeLectures("1,5"));
        assertEquals("Lectures: are missing.", lectureService.removeLectures("5"));
    }

    @Test
    @DisplayName("Remove one lecture from empty array: expected false ")
    void removeLecturesByIntTest() {
        assertFalse(lectureService.removeLectures(5));
        assertTrue(lectureService.removeLectures(1));
    }


}