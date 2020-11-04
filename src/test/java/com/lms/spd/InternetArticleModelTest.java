package com.lms.spd;

import com.lms.spd.models.InternetArticleModel;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class InternetArticleModelTest {

    @Test
    void print() {
        InternetArticleModel internetArticles = new InternetArticleModel("Java", "Aleksey", "google");
        assertEquals(" Internet articles: Java Author: Aleksey Web address: google", internetArticles.print());
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