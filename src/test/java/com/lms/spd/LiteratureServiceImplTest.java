package com.lms.spd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureServiceImplTest {

    LiteratureServiceImpl literatureService = new LiteratureServiceImpl();

    @Test
    void removeLiterature() {
        Literature[] literature = {
                new Book("Java", "Unknown", "Unknown", 2020),
                new JournalArticle("Java", "Unknown", "Unknown", 5)};

        literatureService.removeLiterature(1, literature);

        Literature[] expected = {
                new JournalArticle("Java", "Unknown", "Unknown", 5)};

        assertArrayEquals(expected,literatureService.removeLiterature(1, literature));
    }
}