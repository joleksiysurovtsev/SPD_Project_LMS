package com.lms.spd;

import com.lms.spd.models.JournalArticleModel;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class JournalArticleModelTest {

    @Test
    void print() {
        JournalArticleModel journalArticle = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 5);
        assertEquals("Article: This keyword {in examples} In the journal java world Journal №: 5", journalArticle.print());
    }

    @Test
    void testToString() {
        JournalArticleModel journalArticle = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 5);
        String expected = "Journal article: \"This keyword {in examples}\", in the journal \"java world\", issue of the journal: 5, author:'Dmitry Denisov'";
        assertEquals(expected, journalArticle.toString());
    }

    @Test
    void testEquals() {
        JournalArticleModel journalArticle = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 5);
        JournalArticleModel journalArticle2 = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 5);
        JournalArticleModel journalArticle3 = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 3);

        assertTrue(journalArticle.equals(journalArticle2));
        assertFalse(journalArticle.equals(journalArticle3));
    }

    @Test
    void testHashCode() {
        JournalArticleModel journalArticle = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 5);
        int hashCode = journalArticle.hashCode();
        assertEquals(hashCode, journalArticle.hashCode());
    }
}