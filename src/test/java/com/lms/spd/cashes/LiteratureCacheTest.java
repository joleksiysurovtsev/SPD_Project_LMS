package com.lms.spd.cashes;

import com.lms.spd.cashes.mocks.DBPostgresLectureRepositoryMock;
import com.lms.spd.cashes.mocks.DBPostgresLiteratureRepositoryMock;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureCacheTest {
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
        LiteratureCache cache = new LiteratureCache(new DBPostgresLiteratureRepositoryMock());
        LiteratureCache instance1 = cache.getInstance();
        LiteratureCache instance = LiteratureCache.getInstance();
        assertEquals(instance1, instance);
    }

    @Test
    void getByID() {
        LiteratureCache cache = new LiteratureCache(new DBPostgresLiteratureRepositoryMock());
        BookModel bookModel = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015, 1);
        assertEquals(bookModel, cache.getByID(1));
    }

    @Test
    void addLiteratire() {
        LiteratureCache cache = new LiteratureCache(new DBPostgresLiteratureRepositoryMock());

        BookModel bookModel = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        BookModel bookModel2 = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015,4);

        Literature lecturetested = cache.addLiteratire(bookModel);
        assertEquals(bookModel2, lecturetested);
    }

    @Test
    void removeLecturesByID() {
        DBPostgresLiteratureRepositoryMock dbPostgresLiteratureRepositoryMock = new DBPostgresLiteratureRepositoryMock();
        LiteratureCache cache = new LiteratureCache(dbPostgresLiteratureRepositoryMock);
        //проверили размер кеша и базы
        assertEquals(cache.getCashedLiteratureList().size(), dbPostgresLiteratureRepositoryMock.getLiteratureList().size());

        //удалили одну
        cache.removeLecturesByID(1);

        assertEquals(cache.getCashedLiteratureList().size(), dbPostgresLiteratureRepositoryMock.getLiteratureList().size());

    }

    @Test
    void getCashedLiteratureList() {
        DBPostgresLiteratureRepositoryMock dbPostgresLiteratureRepositoryMock = new DBPostgresLiteratureRepositoryMock();
        LiteratureCache cache = new LiteratureCache(dbPostgresLiteratureRepositoryMock);
        assertEquals(cache.getCashedLiteratureList(), dbPostgresLiteratureRepositoryMock.getLiteratureList());
    }

    @Test
    void getLiteraturesBYLectureID() {
        //TODO
    }
}