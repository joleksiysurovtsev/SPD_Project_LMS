package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.exceptions.ListIsEmptyException;
import com.lms.spd.models.interfaces.Lecture;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LMSConsolePrinterTest")
class LMSConsolePrinterTest {

    @Test
    @Order(38)
    @DisplayName("printAllLectureExceptionTest")
    @Description("The method should return an error if the input is an empty sheet")
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