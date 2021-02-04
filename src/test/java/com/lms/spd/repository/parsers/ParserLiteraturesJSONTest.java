package com.lms.spd.repository.parsers;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserLiteraturesJSONTest {

    @AfterEach
    @BeforeEach
    public void cleanUpFiles() {
        File targetFile = new File("src/test/resources/json/Lectures.json");
        File targetFile2 = new File("src/test/resources/json/Literatures.json");
        targetFile.delete();
        targetFile2.delete();
        ParserLecturesJSON.setURL("src/test/resources/json/");
        ParserLiteraturesJSON.seturl("src/test/resources/json/");
    }

    @Test
    @Order(25)
    void parseLiteratureJSON() {

        Literature literature = new BookModel("testBook", "testautor", "testgenre", 1999);
        List<Literature> testListL = new ArrayList<>();
        testListL.add(literature);
        ParserLiteraturesJSON.parseLiteraturesInJSON(testListL);
        List<Literature> resultListL = ParserLiteraturesJSON.parseLiteraturesFromJSON();
        assertEquals(resultListL, testListL);
    }

    @Test
    @Order(26)
    void parseLiteratureJSON3() {

        List<Literature> resultListL = ParserLiteraturesJSON.parseLiteraturesFromJSON();
        List<Literature> testListL = new ArrayList<>();
        assertEquals(testListL, resultListL);
    }

    @Test
    @Order(27)
    void parseLiteratureJSON4() {
        String url = "src/test/resources/json/";
        assertEquals(url, ParserLecturesJSON.getDirName());
    }
}