package com.lms.spd;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureValidatorTest {
    LiteratureValidator utilsHelper = new LiteratureValidator();

    @Test
    void createJournal() {
        Literature journal = new JournalArticleModel("Java", "Ekkel", "Programming", 2020);
        Literature journal2 = new JournalArticleModel("Unknown", "Unknown", "Unknown", 2020);
        Literature journal3 = new JournalArticleModel("Unknown", "Unknown", "Unknown", 0);
        assertEquals(journal, utilsHelper.createJournal("Java", "Ekkel", "Programming", "2020"));
        assertEquals(journal2, utilsHelper.createJournal("", "", "", "2020"));
        assertEquals(journal3, utilsHelper.createJournal("", "", "", "err"));

    }

    @Test
    void createInternetArticles() {
        Literature book = new BookModel("Java", "Ekkel", "Programming", 2020);
        Literature book2 = new BookModel("Unknown", "Unknown", "Unknown", 2020);
        Literature book3 = new BookModel("Unknown", "Unknown", "Unknown", 0);
        assertEquals(book, utilsHelper.createBook("Java", "Ekkel", "Programming", "2020"));
        assertEquals(book2, utilsHelper.createBook("", "", "", "2020"));
        assertEquals(book3, utilsHelper.createBook("", "", "", "err"));
    }

    @Test
    void createBook() {
        Literature internetArticle = new InternetArticleModel("Java", "Ekkel", "Programming");
        Literature internetArticle2 = new InternetArticleModel("Unknown", "Unknown", "Unknown");

        assertEquals(internetArticle, utilsHelper.createInternetArticles("Java", "Ekkel", "Programming"));
        assertEquals(internetArticle2, utilsHelper.createInternetArticles("", "", ""));

    }
}