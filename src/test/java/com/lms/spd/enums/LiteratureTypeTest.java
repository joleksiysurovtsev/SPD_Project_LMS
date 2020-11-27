package com.lms.spd.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureTypeTest {

    @Test
    void getValueByNumber() {
        assertEquals(LiteratureType.BOOK,LiteratureType.getValueByNumber(1));
        assertEquals(LiteratureType.JOURNAL_ARTICLE,LiteratureType.getValueByNumber(2));
        assertEquals(LiteratureType.INTERNET_ARTICLE,LiteratureType.getValueByNumber(3));
    }

    @Test
    void toListString() {
        String expected = "1. BOOK\n" +
                "2. JOURNAL_ARTICLE\n" +
                "3. INTERNET_ARTICLE\n";

        assertEquals(expected,LiteratureType.toListString());

    }
}