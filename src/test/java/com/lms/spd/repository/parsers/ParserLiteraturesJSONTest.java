package com.lms.spd.repository.parsers;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserLiteraturesJSONTest {

    private static File newFile = new File("src/test/resources/json/Literatures.json");

    @BeforeAll // Перед началом тестов создаём новый фаил с тестами
    static void clearTheFileForTheTestingest() {
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        newFile.delete();
        if (!newFile.exists()) {
            try {
                Files.createFile(newFile.toPath());
            } catch (IOException e) {
                System.err.println("unable to create file");
            }
        }
    }


    @Test
    void parseLiteratureJSON() {
        newFile.delete();
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
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        File file = new File("src/test/resources/json/Literatures.json");
        assertEquals(ParserLiteraturesJSON.getFile(), file);
    }

    @AfterAll
    static void deleteFile() {
        newFile.delete();
    }
}