package com.lms.spd.models;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JournalArticleModelTest {

    @Test
    @Order(16)
    void testEquals() {
        JournalArticleModel journalArticle = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 5);
        JournalArticleModel journalArticle2 = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 5);
        JournalArticleModel journalArticle3 = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 3);

        assertEquals(journalArticle2, journalArticle);
        assertNotEquals(journalArticle3, journalArticle);
    }

    @Test
    @Order(17)
    void testHashCode() {
        JournalArticleModel journalArticle = new JournalArticleModel("This keyword {in examples}", "Dmitry Denisov", "java world", 5);
        int hashCode = journalArticle.hashCode();
        assertEquals(hashCode, journalArticle.hashCode());
    }
}