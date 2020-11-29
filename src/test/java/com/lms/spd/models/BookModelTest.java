package com.lms.spd.models;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class BookModelTest {

    @Test
    void print() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        BookModel testBook = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        assertEquals(" Book: Философия Java Author: Брюс Эккель Genre: Computer Science Publishing in: 2015year\t Date the resource was added: "+sdf.format(testBook.getDateResourceWasAdded().getTime())+" ID 0", testBook.print());
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
        assertEquals(testBook2, testBook);
        assertNotEquals(testBook3, testBook);
    }

    @Test
    void testHashCode() {
        BookModel testBook = new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015);
        int hashCode = testBook.hashCode();
        assertEquals(hashCode, testBook.hashCode());
    }
}