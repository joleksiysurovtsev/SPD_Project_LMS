package com.lms.spd.repository.parsers;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserLiteraturesJSONTest {
    private static File newFileLectures = new File("src/test/resources/json/Lectures.json");
    private static File newFileLiteratures = new File("src/test/resources/json/Literatures.json");

    @BeforeAll // Перед началом тестов создаём новый фаил с тестами
    static void clearTheFileForTheTestingest() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        newFileLiteratures.delete();
        newFileLectures.delete();
    }


    @Test
    void parseLiteratureJSON() {
        Literature literature = new BookModel("testBook","testautor","testgenre",1999,1);
        List<Literature> testListL = new ArrayList<>();
        testListL.add(literature);
        ParserLiteraturesJSON.parseLiteraturesInJSON(testListL);
        List<Literature> resultListL = ParserLiteraturesJSON.parseLiteraturesFromJSON();
        assertEquals(resultListL, testListL);
    }

    @Test
    void parseLiteratureJSON3() {
        List<Literature> resultListL = ParserLiteraturesJSON.parseLiteraturesFromJSON();
        List<Literature> testListL = new ArrayList<>();
        assertEquals(testListL, resultListL);
    }

    @Test
    void parseLiteratureJSON4() {
        File file = new File("src/test/resources/json/Literatures.json");
        assertEquals(ParserLiteraturesJSON.getFile(), file);
    }

    @AfterAll
    static void deleteFile() {
        newFileLiteratures.delete();
        newFileLectures.delete();
    }
}