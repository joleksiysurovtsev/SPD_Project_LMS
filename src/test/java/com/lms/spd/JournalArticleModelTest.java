package com.lms.spd;

import com.lms.spd.models.JournalArticleModel;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class JournalArticleModelTest {

    @Test
    void print() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        JournalArticleModel journalArticle = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 5);
        System.setOut(new PrintStream(output));
        journalArticle.print();

        assertEquals("Article: This keyword {in examples} In the journal java world Journal №: 5\n", output.toString());
        System.setOut(null);
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
        JournalArticleModel journalArticle3 = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world");

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