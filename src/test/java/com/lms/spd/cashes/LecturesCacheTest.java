package com.lms.spd.cashes;

import com.lms.spd.cashes.mocks.DBPostgresLectureRepositoryMock;
import com.lms.spd.cashes.mocks.DBPostgresLiteratureRepositoryMock;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LecturesCacheTest {

    @BeforeEach
    public void updateEvents() {
        DBPostgresLectureRepositoryMock dbPostgresLectureRepositoryMock = new DBPostgresLectureRepositoryMock();
        LecturesCache.getInstance().setLectureRepository(dbPostgresLectureRepositoryMock);
        dbPostgresLectureRepositoryMock.updates();
        LecturesCache.getInstance().updateCashedLectures();

        DBPostgresLiteratureRepositoryMock dbPostgresLiteratureRepositoryMock = new DBPostgresLiteratureRepositoryMock();
        LiteratureCache.getInstance().setLiteratureRepository(dbPostgresLiteratureRepositoryMock);
        dbPostgresLiteratureRepositoryMock.updates();
        LiteratureCache.getInstance().updateCashedLiteratures();
    }


    @Test
    void getInstance() {
        LecturesCache cache = new LecturesCache(new DBPostgresLectureRepositoryMock());
        LecturesCache instance1 = cache.getInstance();
        LecturesCache instance = LecturesCache.getInstance();
        assertEquals(instance1, instance);
    }

    @Test
    void getByID() {
        LecturesCache cache = new LecturesCache(new DBPostgresLectureRepositoryMock());
        Calendar calendar = new GregorianCalendar(2020, 1, 16);
        List<Literature> literatures = new ArrayList<>();
        Lecture lecture = new LectureIModel(LectureType.JAVA_CORE, "nameOfLecture1", literatures, "lectorName1", calendar, 1);
        assertEquals(lecture, cache.getByID(1));
    }

    @Test
    void addLecture() {
        LecturesCache cache = new LecturesCache(new DBPostgresLectureRepositoryMock());
        Calendar calendar = new GregorianCalendar(2020, 1, 16);
        List<Literature> literatures = new ArrayList<>();
        Lecture lecture = new LectureIModel(LectureType.JAVA_CORE, "nameOfLecture1", literatures, "lectorName1", calendar, 4);
        Lecture lecture2 = new LectureIModel(LectureType.JAVA_CORE, "nameOfLecture1", literatures, "lectorName1", calendar);
        Lecture lecturetested = cache.addLecture(lecture2);
        assertEquals(lecture, lecturetested);
    }

    @Test
    void removeLecturesByID() {
        DBPostgresLectureRepositoryMock dbPostgresLectureRepositoryMock = new DBPostgresLectureRepositoryMock();
        LecturesCache cache = new LecturesCache(dbPostgresLectureRepositoryMock);
        //проверили размер кеша и базы
        assertEquals(cache.getCashedLectureList().size(), dbPostgresLectureRepositoryMock.getLectureTestList().size());

        //удалили одну
        cache.removeLecturesByID(1);

        assertEquals(cache.getCashedLectureList().size(), dbPostgresLectureRepositoryMock.getLectureTestList().size());
    }

    @Test
    void addLinkLiteratureLectures() {
        //TODO: дописать тест когда будет готов мок на литературу
    }

    @Test
    void getCashedLectureList() {
        LecturesCache cache = new LecturesCache(new DBPostgresLectureRepositoryMock());
        DBPostgresLectureRepositoryMock dbPostgresLectureRepositoryMock = new DBPostgresLectureRepositoryMock();
        List<Lecture> cashedLectureList = cache.getCashedLectureList();
        List<Lecture> lectureList = dbPostgresLectureRepositoryMock.readAll();
        assertEquals(cashedLectureList, lectureList);
    }

}