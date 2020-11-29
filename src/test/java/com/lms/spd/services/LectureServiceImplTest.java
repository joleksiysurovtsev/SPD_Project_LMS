package com.lms.spd.services;

import com.lms.spd.enums.LectureType;
import com.lms.spd.exceptions.NullLectureException;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.LectureRepository;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectureServiceImplTest {
    private static File file = new File("src/main/resources/json/Lectures.json");

    @Before // Перед началом тестов создаём новый фаил с тестами
    public void clearTheFileForTheTestingest() {
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
    void getLectures() {

        LectureServiceImpl lsImpl = new LectureServiceImpl();
        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);

        LectureRepository lre = new LectureRepository();
        lre.setAll(testListL); //запписали в фаил лекцию для проверки

        //ожидаем получить ту лекцию которую записали
        assertEquals(testListL, lsImpl.getLectures());
    }

    @Test
    void setLectures() {
        LectureRepository pr = new LectureRepository();
        LectureServiceImpl lsImpl = new LectureServiceImpl();
        LectureIModel lectureIModel = new LectureIModel("testLect");
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);

        //spotted a lecture
        lsImpl.setLectures(testListL);

        //expect to receive the lecture that we have attended
        assertEquals(testListL, pr.getAll());
    }

    @Test
    void getSelectedLecture() throws NullLectureException {

        LectureServiceImpl lectureService = new LectureServiceImpl();

        LectureIModel lectureIModel = new LectureIModel(LectureType.JAVA_CORE, "TestL1", null, "testLector", new GregorianCalendar(2005, 10, 12), 1);
        LectureIModel lectureIModel2 = new LectureIModel(LectureType.JAVA_CORE, "TestL2", null, "testLector", new GregorianCalendar(2005, 10, 12), 2);
        List<Lecture> testListL = new ArrayList<>();
        testListL.add(lectureIModel);
        testListL.add(lectureIModel2);

        lectureService.setLectures(testListL);

        lectureService.setSelectedLecture(1);
        assertEquals(lectureIModel, lectureService.getSelectedLecture());
        lectureService.setSelectedLecture(2);
        assertEquals(lectureIModel2, lectureService.getSelectedLecture());
    }

    @Test
    void addLecture() {
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

        //ожидаем лист из двух лекций
        List<Lecture> expectedlist = new ArrayList<>();
        expectedlist.add(lectureIModel);
        expectedlist.add(lectureIModel2);

        List<Lecture> actuallist = ParserLecturesJSON.parseLecturesFromJSON();
        assertEquals(expectedlist, actuallist);
    }

    @Test
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

    @After
    public void deleteFile() {
        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
        file.delete();
    }
}