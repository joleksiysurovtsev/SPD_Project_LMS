package com.lms.spd;

import com.lms.spd.models.InternetArticleModel;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class InternetArticleModelTest {

    @Test
    void print() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        InternetArticleModel internetArticles = new InternetArticleModel("Java", "Aleksey", "Unknown");
        assertEquals(" Internet articles: Java Author: Aleksey Date the resource was added: "+sdf.format(internetArticles.getDateResourceWasAdded().getTime())+" ID 0", internetArticles.print());
    }

    @Test
    void testToString() {
        InternetArticleModel internetArticles = new InternetArticleModel("Caracteres de escape em Java", "Oleksandr Klymenko", "https://javarush.ru/groups/posts/614-----ehkranirovanie-simvolov-v-java");
        String expected = " Internet articles:  Caracteres de escape em Java, author= Oleksandr Klymenko URL https://javarush.ru/groups/posts/614-----ehkranirovanie-simvolov-v-java";
        assertEquals(expected, internetArticles.toString());
    }

    @Test
    void testEquals() {
        InternetArticleModel internetArticles = new InternetArticleModel("Java", "Aleksey", "google");
        InternetArticleModel internetArticles2 = new InternetArticleModel("Java", "Aleksey", "google");
        InternetArticleModel internetArticles3 = new InternetArticleModel("Java2", "Aleksey", "google");
        assertTrue(internetArticles.equals(internetArticles2));
        assertFalse(internetArticles.equals(internetArticles3));
    }

    @Test
    void testHashCode() {
        InternetArticleModel internetArticles = new InternetArticleModel("Java", "Aleksey", "google");
        int hashCode = internetArticles.hashCode();
        assertEquals(hashCode, internetArticles.hashCode());
    }
}