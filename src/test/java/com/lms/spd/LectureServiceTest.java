package com.lms.spd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lecture Service")
class LectureServiceTest {
//    private LectureService lectureService;
//
//    @BeforeEach
//    void createLectureService() {
//        lectureService = new LectureService();
//        Lecture[] lectures = {
//                new Lecture(1, "BufferedReader."),
//                new Lecture(2, "Writes text to."),
//                new Lecture(3, "Core Java API"),
//        };
//        lectureService.setLectures(lectures);
//    }
//
//
//    @Test
//    void addNewLiterature() {
//        lectureService.setSelectedLecture(0);
//        String newBook = "New Book";
//        Literature[] arrLit = {new Literature("New Book")};
//        lectureService.addNewLiterature(newBook);
//        assertArrayEquals(lectureService.getSelectedLecture().getLiterature(), arrLit);
//    }
//
//    @Test
//    void removeLiterature() {
//        //Выбрали лекциию
//        lectureService.setSelectedLecture(0);
//        String newBook = "New Book";
//        String newBook2 = "New Book2";
//        //Добавили в неё книги
//        lectureService.addNewLiterature(newBook);
//        lectureService.addNewLiterature(newBook2);
//        //Удалили первую (метод удаляет по номеру)
//        lectureService.removeLiterature(1);
//        Literature[] testArrLit = {new Literature("New Book2")};
//        assertArrayEquals(lectureService.getSelectedLecture().getLiterature(), testArrLit);
//    }
//
//    @Test
//    void removeLiterature2() {
//        //Выбрали лекциию
//        lectureService.setSelectedLecture(0);
//        String newBook = "New Book";
//        //Добавили в неё книги
//        lectureService.addNewLiterature(newBook);
//        //Удалили первую (метод удаляет по номеру)
//        lectureService.removeLiterature(1);
//        Literature[] testArrLit = new Literature[0];
//        assertArrayEquals(lectureService.getSelectedLecture().getLiterature(), testArrLit);
//    }
//

//
//
//
//    @Test
//    @DisplayName("setSelectedLecture method selects by position in the array")
//    void getSelectedLecture() {
//        lectureService.setSelectedLecture(0);
//        Lecture expectedLecture = new Lecture(1, "BufferedReader.");
//        assertEquals(expectedLecture, lectureService.getSelectedLecture());
//    }
//
//
//    @Test
//    @DisplayName("Add new lecture to array only name: "
//            + "expected: one more lecture will be added to the end of the lecture array")
//    void addLecture() {
//        Lecture[] expectedLectures = {
//                new Lecture(1, "BufferedReader."),
//                new Lecture(2, "Writes text to."),
//                new Lecture(3, "Core Java API"),
//                new Lecture(4, "JUnit Test", new Literature[0])
//        };
//        lectureService.addLecture("JUnit Test", new Literature[0]);
//        assertArrayEquals(expectedLectures, lectureService.getLectures());
//    }
//
//    @Test
//    @DisplayName("Add new lecture to array only name: "
//            + "expected: a lecture will be added to the array"
//            + " of lectures and will become the number specified in the method")
//    void addLecturePlusNumber() {
//        Lecture[] expectedLectures = {
//                new Lecture(1, "BufferedReader."),
//                new Lecture(2, "Writes text to."),
//                new Lecture(3, "JUnit Test", new Literature[0]),
//                new Lecture(4, "Core Java API")
//        };
//        lectureService.addLecturePlusNumber(3, "JUnit Test", new Literature[0]);
//        assertArrayEquals(expectedLectures, lectureService.getLectures());
//    }
//
//    @Test
//    @DisplayName("Remove one lecture from an array. Expecting an array without a third Lecture"
//            + "\n and that the removeLectures() will return the Boolean value true ")
//    void removeLecturesByInt() {
//        Lecture[] lecturesAssert = {
//                new Lecture(1, "BufferedReader."),
//                new Lecture(2, "Writes text to."),
//        };
//
//        boolean successful = lectureService.removeLectures(3);
//        assertAll(
//                () -> assertArrayEquals(lecturesAssert, lectureService.getLectures()),
//                () -> assertTrue(successful)
//        );
//    }
//
//    @Test
//    @DisplayName("Remove multiple lectures from array")
//    void removeLecturesByString() {
//        Lecture[] lecturesAssert = {new Lecture(1, "BufferedReader.")};
//
//        String actualString = lectureService.removeLectures("2, 3");
//        String expectedString = "Lectures: \u001B[33;1m\"2\"\u001B[0m, \u001B[33;1m\"3\"\u001B[0m, successfully removed the rest are missing.";
//        assertAll(
//                () -> assertArrayEquals(lecturesAssert, lectureService.getLectures()),
//                () -> assertEquals(expectedString, actualString)
//        );
//    }
//
//    @Nested
//    @DisplayName("If Lectures array is empty")
//    class EmptyLecturesArr {
//
//        @BeforeEach
//        @DisplayName("Created an empty service ")
//        void createLectureService() {
//            lectureService = new LectureService();
//            Lecture[] lectures = new Lecture[0];
//            lectureService.setLectures(lectures);
//        }
//
//        @Test
//        @DisplayName("Remove one lecture from empty array: expected false ")
//        void removeLecturesByIntTest() {
//            assertFalse(lectureService.removeLectures(1));
//        }
//
//        @Test
//        @DisplayName("Remove one lecture from empty array: expected String 'Lectures: are missing.' ")
//        void removeLecturesByStringTest() {
//            assertEquals("Lectures: are missing.", lectureService.removeLectures("1,2"));
//        }
//    }


}