package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.exceptions.ListIsEmptyException;
import com.lms.spd.models.interfaces.Lecture;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LMSConsolePrinterTest {

    @Test
    void printAllLectureExceptionTest() {
        LMSConsolePrinter printer = new LMSConsolePrinter();
        List<Lecture> testLectures = new ArrayList<>();

        assertThrows(ListIsEmptyException.class, () -> {
            printer.printAllLectureTable(testLectures);
        });

        assertThrows(ListIsEmptyException.class, () -> {
            printer.printLectureListByType(LectureType.DB, testLectures);
        });

        assertThrows(ListIsEmptyException.class, () -> {
            printer.printLectureListByNumber("LectureType.DB", testLectures);
        });

    }

}