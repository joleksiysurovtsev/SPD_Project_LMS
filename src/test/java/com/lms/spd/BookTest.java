package com.lms.spd;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void print() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Book testBook = new Book("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        System.setOut(new PrintStream(output));
        testBook.print();

        assertEquals("Book: Философия Java Author: Брюс Эккель Genre: Computer Science Year of publishing: 2015\n", output.toString());
        System.setOut(null);
    }

    @Test
    void testToString() {
        Book testBook = new Book("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        String expected = "Book: Философия Java, author: 'Брюс Эккель' genre 'Computer Science', published in year 2015";
        assertEquals(expected, testBook.toString());
    }

    @Test
    void testEquals() {
        Book testBook = new Book("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        Book testBook2 = new Book("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        Book testBook3 = new Book("Философия Java", "Брюс Эккель", "Computer Science", 2020);
        assertTrue(testBook.equals(testBook2));
        assertFalse(testBook.equals(testBook3));
    }

    @Test
    void testHashCode() {
        Book testBook = new Book("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        int hashCode = testBook.hashCode();
        assertEquals(hashCode, testBook.hashCode());
    }
}