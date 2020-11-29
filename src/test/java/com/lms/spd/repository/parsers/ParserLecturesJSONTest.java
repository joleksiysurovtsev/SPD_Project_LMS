package com.lms.spd.repository.parsers;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserLecturesJSONTest {
    private static File newFile = new File("src/test/resources/json/Lectures.json");
    @BeforeAll // Перед началом тестов создаём новый фаил с тестами
    static void clearTheFileForTheTestingest() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
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
    void parseLecturesJSON() {
        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
            ParserLecturesJSON.parseLecturesInJSON(testListL);
        List<Lecture> resultListL = ParserLecturesJSON.parseLecturesFromJSON();
        assertEquals(resultListL, testListL);
    }


    @Test
    void parseLecturesJSON2() {
        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
            ParserLecturesJSON.parseLecturesInJSON(testListL);
        String line = null;
        try (BufferedReader reader = Files.newBufferedReader(newFile.toPath())) {
            line = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("{\"@ class\":\"com.lms.spd.models.LectureIModel\",\"NameOfLecture\":\"testLect\",\"List literatures\":null,\"Date of lecture\":null,\"Lector Name\":null,\"Lecture type\":null,\"Lecture ID\":0}", line);
    }


    @Test
    void parseLecturesJSON3() {
        newFile.delete();
        List<Lecture> resultListL = ParserLecturesJSON.parseLecturesFromJSON();
        List<Lecture> testListL = new ArrayList<>();
        assertEquals(testListL, resultListL);
    }

    @Test
    void parseLecturesJSON4() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        File file = new File("src/test/resources/json/Lectures.json");
        assertEquals(ParserLecturesJSON.getFile(), file);
    }


    @AfterAll
    static void deleteFile() {
        ParserLecturesJSON.seturl("src/test/resources/json/Literatures.json");
        newFile.delete();
    }
}