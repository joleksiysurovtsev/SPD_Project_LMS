package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.exceptions.ListIsEmptyException;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LMSConsolePrinterTest {
    private static File file = new File("src/test/resources/json/Lectures.json");
    private  static File file2 = new File("src/test/resources/json/Literatures.json");

    @Test
    void printAllLectureExceptionTest() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        file2.delete();
        file.delete();
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
        file2.delete();
        file.delete();
    }

}