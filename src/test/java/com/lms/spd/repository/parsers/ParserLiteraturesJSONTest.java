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

    @Test
    @Order(25)
    void parseLiteratureJSON() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        newFileLiteratures.delete();
        newFileLectures.delete();
        Literature literature = new BookModel("testBook","testautor","testgenre",1999,1);
        List<Literature> testListL = new ArrayList<>();
        testListL.add(literature);
        ParserLiteraturesJSON.parseLiteraturesInJSON(testListL);
        List<Literature> resultListL = ParserLiteraturesJSON.parseLiteraturesFromJSON();
        assertEquals(resultListL, testListL);
        newFileLiteratures.delete();
        newFileLectures.delete();

    }

    @Test
    @Order(26)
    void parseLiteratureJSON3() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        newFileLiteratures.delete();
        newFileLectures.delete();
        List<Literature> resultListL = ParserLiteraturesJSON.parseLiteraturesFromJSON();
        List<Literature> testListL = new ArrayList<>();
        assertEquals(testListL, resultListL);
        newFileLiteratures.delete();
        newFileLectures.delete();
    }

    @Test
    @Order(27)
    void parseLiteratureJSON4() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        newFileLiteratures.delete();
        newFileLectures.delete();
        File file = new File("src/test/resources/json/Literatures.json");
        assertEquals(ParserLiteraturesJSON.getFile(), file);
        newFileLiteratures.delete();
        newFileLectures.delete();
    }

}