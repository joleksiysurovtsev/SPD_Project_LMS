package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.exceptions.ListIsEmptyException;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    LMSConsolePrinter lMSConsolePrinter;

    @org.junit.Test
    public void testPrintAllLectureTable() throws Exception {
        lMSConsolePrinter.printAllLectureTable(Arrays.<Lecture>asList(new LectureIModel("nameOfLecture")));
    }

    @org.junit.Test
    public void testPrintPreviewLectureList() throws Exception {
        lMSConsolePrinter.printPreviewLectureList(Arrays.<Lecture>asList(new LectureIModel("nameOfLecture")));
    }

    @org.junit.Test
    public void testPrintLectureListByType() throws Exception {
        lMSConsolePrinter.printLectureListByType(LectureType.JAVA_CORE, Arrays.<Lecture>asList(new LectureIModel("nameOfLecture")));
    }

    @org.junit.Test
    public void testPrintLectureListByNumber() throws ListIsEmptyException {
        lMSConsolePrinter.printLectureListByNumber("strWithNum", Arrays.<Lecture>asList(new LectureIModel("nameOfLecture")));
    }

    @org.junit.Test
    public void testPrintLectureTable() {
        lMSConsolePrinter.printLectureTable(new LectureIModel("nameOfLecture"));
    }

    @org.junit.Test
    public void testSortLitByDateAndType() {
        List<Literature> result = lMSConsolePrinter.sortLitByDateAndType(Collections.singletonList(new BookModel("title", "author", null, 0, 0)));
        Assert.assertEquals(Collections.<Literature>singletonList(new BookModel("title", "author", null, 0, 0)), result);
    }


    @org.junit.Test
    public void testPrintListLit()  {
        lMSConsolePrinter.printListLit(Collections.singletonList(new BookModel("title", "author", null, 0, 0)));
    }

    @org.junit.Test
    public void testShowAllLectureInfo()   {
        lMSConsolePrinter.showAllLectureInfo(new LectureIModel("nameOfLecture"));
    }

    @org.junit.Test
    public void testPrintLectureListByType2() throws Exception {
        lMSConsolePrinter.printLectureListByType(LectureType.JAVA_CORE, new HashMap<>() {{
            put(LectureType.JAVA_CORE, Collections.singletonList(new LectureIModel("nameOfLecture")));
        }});
    }

    @org.junit.Test
    public void testPrintLectureListByTypeAndDate() throws Exception {
        lMSConsolePrinter.printLectureListByTypeAndDate(LectureType.JAVA_CORE, new HashMap<>() {{
            put(LectureType.JAVA_CORE, Collections.singletonList(new LectureIModel("nameOfLecture")));
        }}, null);
    }

    @org.junit.Test
    public void testPrintLectureListStatistics() throws Exception {
        lMSConsolePrinter.printLectureListStatistics(Collections.singletonList(new LectureIModel("nameOfLecture")));
    }

}