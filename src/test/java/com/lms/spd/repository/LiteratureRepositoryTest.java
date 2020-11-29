package com.lms.spd.repository;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureRepositoryTest {

    @Test
    void setAllTest() {
        File newFile = new File("src/test/resources/json/Literatures.json");
        Literature booktest = new BookModel("testTitle", "testAuthor", "testGenre", 1999, 1);
        Calendar calendar = new GregorianCalendar(2020, 02, 19);
        booktest.setDateResourceWasAdded(calendar);
        List<Literature> literature = new ArrayList<>();
        literature.add(booktest);
        ParserLecturesJSON.seturl("src/test/resources/json/Literatures.json");
        LiteratureRepository lR = new LiteratureRepository();
        lR.setAll(literature);

        String line = null;
        try (BufferedReader reader = Files.newBufferedReader(newFile.toPath())) {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals("{\"@ class\":\"com.lms.spd.models.BookModel\",\"Title\":\"testBook\",\"Author\":\"testautor\",\"Genre\":\"testgenre\",\"PublishedInYear\":1999,\"Literature type\":null,\"Date resource was added\":\"29-11-2020\",\"ID\":1}", line);
    }

    @Test
    void getAllTests() {
        File newFile = new File("src/test/resources/json/Literatures.json");
        Literature booktest = new BookModel("testTitle", "testAuthor", "testGenre", 1999, 1);
        Calendar calendar = new GregorianCalendar(2020, 02, 19);
        booktest.setDateResourceWasAdded(calendar);
        List<Literature> literature = new ArrayList<>();
        literature.add(booktest);
        ParserLecturesJSON.seturl("src/test/resources/json/Literatures.json");
        LiteratureRepository lR = new LiteratureRepository();
        lR.setAll(literature);
        assertEquals(literature, lR.getAll());
    }
}