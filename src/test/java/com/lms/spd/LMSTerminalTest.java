package com.lms.spd;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LMSTerminalTest {
    LMSTerminal terminal = new LMSTerminal();

    @Test
    void createJournal() {
        Literature journal = new JournalArticleModel("Java", "Ekkel", "Programming", 2020);
        Literature journal2 = new JournalArticleModel("Unknown", "Unknown", "Unknown", 2020);
        Literature journal3 = new JournalArticleModel("Unknown", "Unknown", "Unknown", 0);
        assertEquals(journal, terminal.createJournal("Java", "Ekkel", "Programming", "2020"));
        assertEquals(journal2, terminal.createJournal("", "", "", "2020"));
        assertEquals(journal3, terminal.createJournal("", "", "", "err"));

    }

    @Test
    void createInternetArticles() {
        Literature book = new BookModel("Java", "Ekkel", "Programming", 2020);
        Literature book2 = new BookModel("Unknown", "Unknown", "Unknown", 2020);
        Literature book3 = new BookModel("Unknown", "Unknown", "Unknown", 0);
        assertEquals(book, terminal.createBook("Java", "Ekkel", "Programming", "2020"));
        assertEquals(book2, terminal.createBook("", "", "", "2020"));
        assertEquals(book3, terminal.createBook("", "", "", "err"));
    }

    @Test
    void createBook() {
        Literature internetArticle = new InternetArticleModel("Java", "Ekkel", "Programming");
        Literature internetArticle2 = new InternetArticleModel("Unknown", "Unknown", "Unknown");

        assertEquals(internetArticle, terminal.createInternetArticles("Java", "Ekkel", "Programming"));
        assertEquals(internetArticle2, terminal.createInternetArticles("", "", ""));

    }
}