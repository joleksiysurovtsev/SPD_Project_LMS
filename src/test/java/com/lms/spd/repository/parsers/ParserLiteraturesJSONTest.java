package com.lms.spd.repository.parsers;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserLiteraturesJSONTest {
    private static File newFileLectures = new File("src/test/resources/json/Lectures.json");
    private static File newFileLiteratures = new File("src/test/resources/json/Literatures.json");

    private static void clearFiles() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        newFileLectures.delete();
        newFileLiteratures.delete();
    }

    @Test
    @Order(25)
    void parseLiteratureJSON() {
        clearFiles();
        Literature literature = new BookModel("testBook","testautor","testgenre",1999,1);
        List<Literature> testListL = new ArrayList<>();
        testListL.add(literature);
        ParserLiteraturesJSON.parseLiteraturesInJSON(testListL);
        List<Literature> resultListL = ParserLiteraturesJSON.parseLiteraturesFromJSON();
        assertEquals(resultListL, testListL);
        clearFiles();
    }

    @Test
    @Order(26)
    void parseLiteratureJSON3() {
        clearFiles();
        List<Literature> resultListL = ParserLiteraturesJSON.parseLiteraturesFromJSON();
        List<Literature> testListL = new ArrayList<>();
        assertEquals(testListL, resultListL);
        clearFiles();
    }

    @Test
    @Order(27)
    void parseLiteratureJSON4() {
        clearFiles();
        File file = new File("src/test/resources/json/Literatures.json");
        assertEquals(ParserLiteraturesJSON.getFile(), file);
        clearFiles();
    }

}