package com.lms.spd.services;

import com.lms.spd.cashes.LecturesCache;
import com.lms.spd.cashes.LiteratureCache;
import com.lms.spd.cashes.mocks.DBLectureRepositoryMock;
import com.lms.spd.cashes.mocks.DBLiteratureRepositoryMock;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.IService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureServiceImplTest {

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
    void getItems() {
        IService<Literature> service = new LiteratureServiceImpl();
        assertEquals(LiteratureCache.getInstance().getCashedLiteratureList(), service.getItems());
    }

    @Test
    void getSelectedItem() {
        IService<Literature> service = new LiteratureServiceImpl();
        BookModel bookModel = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        service.setSelectedItem(1);
        assertEquals(new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015), service.getSelectedItem());
    }

    @Test
    void addItem() {
        IService<Literature> service = new LiteratureServiceImpl();
        BookModel bookModel1 = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        BookModel bookModel2 = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        assertEquals(bookModel2, service.addItem(bookModel1));
    }
}