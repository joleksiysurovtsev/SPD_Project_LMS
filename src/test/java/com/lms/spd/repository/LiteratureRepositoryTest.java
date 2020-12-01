package com.lms.spd.repository;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureRepositoryTest {
    private static File newfileLectures = new File("src/test/resources/json/Lectures.json");
    private static File newFileLiteratures = new File("src/test/resources/json/Literatures.json");

    private static void clearFiles() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        newfileLectures.delete();
        newFileLiteratures.delete();
    }


    @Test
    @Order(29)
    void setAllTest() {
        clearFiles();
        LiteratureRepository lR = new LiteratureRepository();
        Literature booktest = new BookModel("testTitle", "testAuthor", "testGenre", 1999, 1);
        booktest.setDateResourceWasAdded(new GregorianCalendar(2020, 02, 19));

        List<Literature> literature = new ArrayList<>();
        literature.add(booktest);

        lR.setAll(literature);

        List<Literature> literature2 = ParserLiteraturesJSON.parseLiteraturesFromJSON();

        assertEquals(literature, literature2);
    }

    @Test
    @Order(30)
    void getAllTests() {
        clearFiles();
        Literature booktest = new BookModel("testTitle", "testAuthor", "testGenre", 1999, 1);
        Calendar calendar = new GregorianCalendar(2020, 02, 19);
        booktest.setDateResourceWasAdded(calendar);
        List<Literature> literature = new ArrayList<>();
        literature.add(booktest);
        LiteratureRepository lR = new LiteratureRepository();
        lR.setAll(literature);

        assertEquals(literature, lR.getAll());
    }
}