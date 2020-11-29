package com.lms.spd.repository;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
    private static File file = new File("src/test/resources/json/Literatures.json");

    @BeforeAll // Перед началом тестов создаём новый фаил с тестами
    static void clearTheFileForTheTestingest() {
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        file.delete();
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                System.err.println("unable to create file");
            }
        }
    }


    @Test
    void setAllTest() {
        LiteratureRepository lR = new LiteratureRepository();
        Literature booktest = new BookModel("testTitle", "testAuthor", "testGenre", 1999, 1);
        Calendar calendar = new GregorianCalendar(2020, 02, 19);
        booktest.setDateResourceWasAdded(calendar);

        List<Literature> literature = new ArrayList<>();
        literature.add(booktest);

        lR.setAll(literature);

        String line = null;
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(literature,ParserLiteraturesJSON.parseLiteraturesFromJSON());
    }

    @Test
    void getAllTests() {
        Literature booktest = new BookModel("testTitle", "testAuthor", "testGenre", 1999, 1);
        Calendar calendar = new GregorianCalendar(2020, 02, 19);
        booktest.setDateResourceWasAdded(calendar);
        List<Literature> literature = new ArrayList<>();
        literature.add(booktest);
        LiteratureRepository lR = new LiteratureRepository();
        lR.setAll(literature);
        assertEquals(literature, lR.getAll());
    }

    @AfterAll
    static void deleteFile() {
        file.delete();
    }
}