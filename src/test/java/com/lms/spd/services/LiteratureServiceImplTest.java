package com.lms.spd.services;

import com.lms.spd.cashes.LecturesCache;
import com.lms.spd.cashes.LiteratureCache;
import com.lms.spd.cashes.mocks.DBPostgresLectureRepositoryMock;
import com.lms.spd.cashes.mocks.DBPostgresLiteratureRepositoryMock;
import com.lms.spd.enums.LectureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.IService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureServiceImplTest {

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
    void getItems() {
        IService<Literature> service = new LiteratureServiceImpl();
        assertEquals(LiteratureCache.getInstance().getCashedLiteratureList(), service.getItems());
    }

    @Test
    void getSelectedItem() {
        IService<Literature> service = new LiteratureServiceImpl();
        BookModel bookModel = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015, 1);
        service.setSelectedItem(1);
        assertEquals(new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015, 1), service.getSelectedItem());
    }

    @Test
    void addItem() {
        IService<Literature> service = new LiteratureServiceImpl();
        BookModel bookModel1 = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        BookModel bookModel2 = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015, 4);
        assertEquals(bookModel2, service.addItem(bookModel1));
    }

    @Test
    void removeItems() {
    }

    @Test
    void getLiteraturesBYLectureID() {
    }
}