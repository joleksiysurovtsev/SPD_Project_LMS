package com.lms.spd.services;

import com.lms.spd.enums.LectureType;
import com.lms.spd.exceptions.NullLectureException;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.LectureRepository;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;
import com.lms.spd.services.interfaces.LectureService;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectureServiceImplTest {
    @AfterEach
    @BeforeEach
    public void cleanUpFiles() {
        File targetFile = new File("src/test/resources/json/Lectures.json");
        File targetFile2 = new File("src/test/resources/json/Literatures.json");
        targetFile.delete();
        targetFile2.delete();
        ParserLecturesJSON.seturl("src/test/resources/json/");
        ParserLiteraturesJSON.seturl("src/test/resources/json/");
    }

    @Test
    @Order(31)
    @Description("Test method to get all lectures: " +
            "Should get the entire list of lectures that have been written to a JSON file")
    void getLectures() {
        LectureServiceImpl lsImpl = new LectureServiceImpl();
        LectureIModel lectureIModel = new LectureIModel("testLect");
        LectureIModel lectureIModel2 = new LectureIModel("testLect2");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        testListL.add(lectureIModel2);

        LectureRepository lre = new LectureRepository();
        //wrote a lecture to a file for verification
        lre.setAll(testListL);

        //expect to receive the lecture we write
        assertEquals(ParserLecturesJSON.parseLecturesFromJSON(), lsImpl.getLectures());
    }

    @Test
    @Order(32)
    @Description("The method should send the entire list of lectures for writing in a JSON file")
    void setLectures() {
        LectureServiceImpl lsImpl = new LectureServiceImpl();
        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);

        //spotted a lecture
        lsImpl.setLectures(testListL);

        //expect to receive the lecture that we have attended
        assertEquals(testListL, ParserLecturesJSON.parseLecturesFromJSON());
    }

    @Test
    @Order(33)
    @Description("Test method that should return a lecture by its ID number")
    void getSelectedLecture() {
        //created a lecture list
        LectureIModel lectureIModel = new LectureIModel(LectureType.JAVA_CORE, "TestL1", new ArrayList<>(), "testLector", new GregorianCalendar(2005, 10, 12), 1);
        LectureIModel lectureIModel2 = new LectureIModel(LectureType.COMMON, "TestL2", new ArrayList<>(), "testLector", new GregorianCalendar(2005, 10, 12), 2);
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        testListL.add(lectureIModel2);

        //wrote the sheet to a file
        ParserLecturesJSON.parseLecturesInJSON(testListL);

        LectureServiceImpl lectureService = new LectureServiceImpl();

        lectureService.setSelectedLecture(1);
        Lecture actual = lectureService.getSelectedLecture();

        assertEquals(lectureIModel, actual);
    }

    @Test
    @Order(34)
    @Description("Testing adding lectures to a JSON file")
    void addLecture() {
        //created a lecture added it to the list
        LectureIModel lectureByTest = new LectureIModel(LectureType.JAVA_CORE, "TestL1", new ArrayList<>(), "testLector", new GregorianCalendar(2020, 01, 4), 1);

        LectureServiceImpl lectureService = new LectureServiceImpl();
        lectureService.addLecture(lectureByTest);

        Lecture actualLecture = ParserLecturesJSON.parseLecturesFromJSON().get(0);
        assertEquals(lectureByTest,actualLecture);
    }

    @Test
    @Order(35)
    void removeLectures() {

        //создали лекцию
        List<Literature> literature = new ArrayList<>();
        literature.add(new BookModel("Title", "author", "genre", 1986, 1));
        LectureIModel lectureIModel = new LectureIModel(LectureType.JAVA_CORE, "TestL1", null, "testLector", new GregorianCalendar(2020, 01, 4), 1);
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(lectureIModel);

        //записал лекцию в фаил
        ParserLecturesJSON.parseLecturesInJSON(lectures);

        //создал вторую лекцию
        List<Literature> literature2 = new ArrayList<>();
        literature2.add(new BookModel("Title2", "author2", "genre", 1986, 2));
        LectureIModel lectureIModel2 = new LectureIModel(LectureType.JAVA_CORE, "TestL1", literature2, "testLector", new GregorianCalendar(2020, 01, 4), 2);

        //добавил её к лекциям
        LectureServiceImpl lectureService = new LectureServiceImpl();
        lectureService.addLecture(lectureIModel2);


        int[] remove = {1};
        //удалил первую лекцию
        lectureService.removeLectures(remove);

        //ожидаем лист c второй лекцией
        List<Lecture> expectedlist = new ArrayList<>();
        expectedlist.add(lectureIModel2);

        List<Lecture> actuallist = ParserLecturesJSON.parseLecturesFromJSON();
        assertEquals(expectedlist, actuallist);
    }
}