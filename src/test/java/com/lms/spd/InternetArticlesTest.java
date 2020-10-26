package com.lms.spd;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class InternetArticlesTest {

    @Test
    void print() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        InternetArticles internetArticles = new InternetArticles("Java", "Aleksey", "google");
        System.setOut(new PrintStream(output));
        internetArticles.print();

        assertEquals("Title: Java Author: Aleksey Web address: google\n", output.toString());
        System.setOut(null);
    }

    @Test
    void testToString() {
        InternetArticles internetArticles = new InternetArticles("Caracteres de escape em Java", "Oleksandr Klymenko", "https://javarush.ru/groups/posts/614-----ehkranirovanie-simvolov-v-java");
        String expected = "Internet articles:  Caracteres de escape em Java, author= Oleksandr Klymenko URL https://javarush.ru/groups/posts/614-----ehkranirovanie-simvolov-v-java";
        assertEquals(expected, internetArticles.toString());
    }

    @Test
    void testEquals() {
        InternetArticles internetArticles = new InternetArticles("Java", "Aleksey", "google");
        InternetArticles internetArticles2 = new InternetArticles("Java", "Aleksey", "google");
        InternetArticles internetArticles3 = new InternetArticles("Java2", "Aleksey", "google");
        assertTrue(internetArticles.equals(internetArticles2));
        assertFalse(internetArticles.equals(internetArticles3));
    }

    @Test
    void testHashCode() {
        InternetArticles internetArticles = new InternetArticles("Java", "Aleksey", "google");
        int hashCode = internetArticles.hashCode();
        assertEquals(hashCode, internetArticles.hashCode());
    }
}