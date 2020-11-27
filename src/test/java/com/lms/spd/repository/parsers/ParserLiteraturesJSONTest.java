package com.lms.spd.repository.parsers;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserLiteraturesJSONTest {

    File newFile = new File("src/test/resources/json/Literatures.json");
    @Test
    void parseLiteratureJSON() {

        newFile.delete();
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");

        Literature literature = new BookModel("testBook","testautor","testgenre",1999,1);
        List<Literature> testListL = new ArrayList<>();
        testListL.add(literature);

        ParserLiteraturesJSON.parseLiteraturesInJSON(testListL);

        List<Literature> resultListL = ParserLiteraturesJSON.parseLiteraturesFromJSON();
        assertEquals(resultListL, testListL);
    }


    @Test
    void parseLiteratureJSON3() {
        ParserLiteraturesJSON.seturl("src/test/resources/json/Lectures.json");
        newFile.delete();
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
}