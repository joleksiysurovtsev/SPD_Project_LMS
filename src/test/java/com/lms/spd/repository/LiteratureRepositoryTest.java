package com.lms.spd.repository;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureRepositoryTest {
    private static File file = new File("src/test/resources/json/Lectures.json");
    private static File file2 = new File("src/test/resources/json/Literatures.json");


    @Test
    void setAllTest() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        file.delete();
        file2.delete();
        try {
            file.createNewFile();
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LiteratureRepository lR = new LiteratureRepository();
        Literature booktest = new BookModel("testTitle", "testAuthor", "testGenre", 1999, 1);
        booktest.setDateResourceWasAdded(new

                GregorianCalendar(2020, 02, 19));

        List<Literature> literature = new ArrayList<>();
        literature.add(booktest);

        lR.setAll(literature);

        List<Literature> literature2 = ParserLiteraturesJSON.parseLiteraturesFromJSON();

        assertTrue(literature.equals(literature2));
        file.delete();
        file2.delete();
    }

    @Test
    void getAllTests() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        try {
            file.createNewFile();
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Literature booktest = new BookModel("testTitle", "testAuthor", "testGenre", 1999, 1);
        Calendar calendar = new GregorianCalendar(2020, 02, 19);
        booktest.setDateResourceWasAdded(calendar);
        List<Literature> literature = new ArrayList<>();
        literature.add(booktest);
        LiteratureRepository lR = new LiteratureRepository();
        lR.setAll(literature);

        //   assertEquals(literature, lR.getAll());
        file.delete();
        file2.delete();
    }
}