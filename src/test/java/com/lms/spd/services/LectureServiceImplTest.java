package com.lms.spd.services;

import com.lms.spd.cashes.mocks.DBLectureRepositoryMock;
import com.lms.spd.cashes.mocks.DBLiteratureRepositoryMock;
import com.lms.spd.cashes.LecturesCache;
import com.lms.spd.cashes.LiteratureCache;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.IService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectureServiceImplTest {

    @BeforeEach
    public void updateEvents() {
        DBLectureRepositoryMock dbPostgresLectureRepositoryMock = new DBLectureRepositoryMock();
        LecturesCache.getInstance().setLectureRepository(dbPostgresLectureRepositoryMock);
        dbPostgresLectureRepositoryMock.updates();
        LecturesCache.getInstance().updateCashedLectures();

        DBLiteratureRepositoryMock dbPostgresLiteratureRepositoryMock = new DBLiteratureRepositoryMock();
        LiteratureCache.getInstance().setLiteratureRepository(dbPostgresLiteratureRepositoryMock);
        dbPostgresLiteratureRepositoryMock.updates();
        LiteratureCache.getInstance().updateCashedLiteratures();
    }

    @Test
    @Order(1)
    void getItems() {
        IService<Lecture> service = new LectureServiceImpl();
        assertEquals(LecturesCache.getInstance().getCashedLectureList(), service.getItems());
    }

    @Test
    @Order(2)
    void setSelectedItem() {
        IService<Lecture> service = new LectureServiceImpl();

        service.setSelectedItem(1);
        Calendar calendar = new GregorianCalendar(2020, 1, 16);
        List<Literature> literatures = new ArrayList<>();

        assertEquals(new LectureIModel(LectureType.JAVA_CORE, "nameOfLecture1", literatures, "lectorName1", calendar, 1), service.getSelectedItem());
    }

    @Test
    @Order(3)
    void addItem() {
        IService<Lecture> service = new LectureServiceImpl();
        Calendar calendar = new GregorianCalendar(2020, 1, 16);
        List<Literature> literatures = new ArrayList<>();
        LectureIModel lectureIModel = new LectureIModel(LectureType.COMMON, "nameOfLecture3", literatures, "lectorName4", calendar);
        LectureIModel lectureIModelExpected = new LectureIModel(LectureType.COMMON, "nameOfLecture3", literatures, "lectorName4", calendar, 4);
        assertEquals(lectureIModelExpected, service.addItem(lectureIModel));
    }

    @Test
    @Order(4)
    void removeItems() {
        IService<Lecture> service = new LectureServiceImpl();
        int size = service.getItems().size();
        int size1 = LecturesCache.getInstance().getCashedLectureList().size();
        assertEquals(size, size1);
        int[] ints = {2};
        service.removeItems(ints);
        assertEquals(service.getItems().size(), size1 - 1);
    }

    @Test
    @Order(5)
    void getLectureListByType() {
        LectureServiceImpl service = new LectureServiceImpl();
        Calendar calendar = new GregorianCalendar(2020, 1, 16);
        List<Literature> literatures = new ArrayList<>();
        List<Lecture> lectureTestList = new ArrayList<>();
        lectureTestList.add(new LectureIModel(LectureType.DB, "nameOfLecture3", literatures, "lectorName4", calendar, 3));
        assertEquals(lectureTestList, service.getLectureListByType(LectureType.DB));
    }

    @Test
    @Order(6)
    void getLectureListByDate() {
    }

    @Test
    void getLecturesByNumber() {
    }

    @Test
    void getLectureListByTypeAndDate() {
    }

    @Test
    void addLinkLiteratureLectures() {
    }

}