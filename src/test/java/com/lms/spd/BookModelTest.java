package com.lms.spd;

import com.lms.spd.models.BookModel;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BookModelTest {

    @Test
    void print() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        BookModel testBook = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        System.setOut(new PrintStream(output));
        testBook.print();

        assertEquals("Book: Философия Java Author: Брюс Эккель Genre: Computer Science Year of publishing: 2015\n", output.toString());
        System.setOut(null);
    }

    @Test
    void testToString() {
        BookModel testBook = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        String expected = "Book: Философия Java, author: 'Брюс Эккель' genre 'Computer Science', published in year 2015";
        assertEquals(expected, testBook.toString());
    }

    @Test
    void testEquals() {
        BookModel testBook = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        BookModel testBook2 = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        BookModel testBook3 = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2020);
        assertTrue(testBook.equals(testBook2));
        assertFalse(testBook.equals(testBook3));
    }

    @Test
    void testHashCode() {
        BookModel testBook = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        int hashCode = testBook.hashCode();
        assertEquals(hashCode, testBook.hashCode());
    }
}