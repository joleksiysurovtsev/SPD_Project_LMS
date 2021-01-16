package com.lms.spd.enums;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LiteratureTypeTest {

    @Test
    @Order(4)
    void getValueByNumber() {
        assertEquals(LiteratureType.BOOK, LiteratureType.getValueByNumber(1));
        assertEquals(LiteratureType.JOURNAL_ARTICLE, LiteratureType.getValueByNumber(2));
        assertEquals(LiteratureType.INTERNET_ARTICLE, LiteratureType.getValueByNumber(3));
    }

    @Test
    public void testGetValueByNumber() {
        assertEquals(LiteratureType.BOOK, LiteratureType.getValueByNumber(1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> LiteratureType.getValueByNumber(0));
    }

    @Test
    public void testToListString() {
        assertEquals("1. BOOK\n2. JOURNAL_ARTICLE\n3. INTERNET_ARTICLE\n", LiteratureType.toListString());
    }

    @Test
    public void testValueOf() {
         assertEquals(LiteratureType.JOURNAL_ARTICLE, LiteratureType.valueOf("JOURNAL_ARTICLE"));
    }

    @Test
    public void testValueOf2() {
        assertEquals(LiteratureType.BOOK, LiteratureType.valueOf("BOOK"));
    }

    @Test
    @Order(5)
    void toListString() {
        String expected = "1. BOOK\n" +
                "2. JOURNAL_ARTICLE\n" +
                "3. INTERNET_ARTICLE\n";
        assertEquals(expected, LiteratureType.toListString());
    }
}