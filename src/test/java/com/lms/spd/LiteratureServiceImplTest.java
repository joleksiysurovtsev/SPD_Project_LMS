package com.lms.spd;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LiteratureServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureServiceImplTest {

    LiteratureServiceImpl literatureService = new LiteratureServiceImpl();

    @Test
    void removeLiterature() {
        Literature[] literature = {
                new BookModel("Java", "Unknown", "Unknown", 2020),
                new JournalArticleModel("Java", "Unknown", "Unknown", 5)};

        literatureService.removeLiterature(1, literature);

        Literature[] expected = {
                new JournalArticleModel("Java", "Unknown", "Unknown", 5)};

        assertArrayEquals(expected,literatureService.removeLiterature(1, literature));
    }
}