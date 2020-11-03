package com.lms.spd.services;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LiteratureServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LiteratureServiceImplTest {

    LiteratureServiceImpl literatureService = new LiteratureServiceImpl();

    @Test
    @DisplayName("removal of literature")
    void removeLiteraturetest() {
        List<Literature> literature = new ArrayList<>();
        literature.add(new BookModel("Java", "Unknown", "Unknown", 2020));
        literature.add(new JournalArticleModel("Java", "Unknown", "Unknown", 5));

        List<Literature> expected = new ArrayList<>();
        expected.add(new JournalArticleModel("Java", "Unknown", "Unknown", 5));

        assertEquals(expected, literatureService.removeLiterature(1, literature));
    }

    @Test
    @DisplayName("deleting literature if there is only one literature")
    void removeLiteraturetest2() {
        List<Literature> literature = new ArrayList<>();
        literature.add(new BookModel("Java", "Unknown", "Unknown", 2020));

        List<Literature> expected = new ArrayList<>();

        assertEquals(expected, literatureService.removeLiterature(1, literature));
    }

    @Test
    @DisplayName("create Journal test")
    void createJournal() {
        Literature journal = new JournalArticleModel("Java", "Ekkel", "Programming", 2020);
        Literature journal2 = new JournalArticleModel("Unknown", "Unknown", "Unknown", 2020);
        Literature journal3 = new JournalArticleModel("Unknown", "Unknown", "Unknown", 0);
        assertEquals(journal, literatureService.createJournal("Java", "Ekkel", "Programming", "2020"));
        assertEquals(journal2, literatureService.createJournal("", "", "", "2020"));
        assertEquals(journal3, literatureService.createJournal("", "", "", "err"));
    }

    @Test
    @DisplayName("create Book test")
    void createBook() {
        Literature book = new BookModel("Java", "Ekkel", "Programming", 2020);
        Literature book2 = new BookModel("Unknown", "Unknown", "Unknown", 2020);
        Literature book3 = new BookModel("Unknown", "Unknown", "Unknown", 0);
        assertEquals(book, literatureService.createBook("Java", "Ekkel", "Programming", "2020"));
        assertEquals(book2, literatureService.createBook("", "", "", "2020"));
        assertEquals(book3, literatureService.createBook("", "", "", "err"));
    }

    @Test
    @DisplayName("create InternetArticles test")
    void createInternetArticles() {
        Literature internetArticle = new InternetArticleModel("Java", "Ekkel", "Programming");
        Literature internetArticle2 = new InternetArticleModel("Unknown", "Unknown", "Unknown");

        assertEquals(internetArticle, literatureService.createInternetArticles("Java", "Ekkel", "Programming"));
        assertEquals(internetArticle2, literatureService.createInternetArticles("", "", ""));
    }
}