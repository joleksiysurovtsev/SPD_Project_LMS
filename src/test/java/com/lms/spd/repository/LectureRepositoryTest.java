package com.lms.spd.repository;

import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectureRepositoryTest {
    private static File file = new File("src/main/resources/json/Lectures.json");
    @BeforeAll // Перед началом тестов создаём новый фаил с тестами
    static void clearTheFileForTheTestingest() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
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
        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        LectureRepository lre = new LectureRepository();
        lre.setAll(testListL);

        assertEquals(testListL,lre.getAll());
    }


    @AfterAll
    static void deleteFile() {
        file.delete();
    }

}