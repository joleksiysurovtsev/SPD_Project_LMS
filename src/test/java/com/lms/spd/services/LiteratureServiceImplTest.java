package com.lms.spd.services;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LiteratureServiceImplTest {

    LiteratureServiceImpl literatureService = new LiteratureServiceImpl();

    @Test
    @DisplayName("add literature")
    void addLiteratureTest() {
        List<Literature> literature = new ArrayList<>();
        Literature addingLit = new JournalArticleModel("Java", "Unknown", "Unknown", 5);

        List<Literature> expected = new ArrayList<>();
        expected.add(new JournalArticleModel("Java", "Unknown", "Unknown", 5));

        assertEquals(expected, literatureService.addLiterature(addingLit,literature));
    }



    @Test
    @DisplayName("removal of literature")
    void removeLiteraturetest() {
        List<Literature> literature = new ArrayList<>();
        literature.add(new BookModel("Java", "Unknown", "Unknown", 2020));
        literature.add(new JournalArticleModel("Java", "Unknown", "Unknown", 5));

        List<Literature> expected = new ArrayList<>();
        expected.add(new JournalArticleModel("Java", "Unknown", "Unknown", 5));

        assertEquals(expected, literatureService.removeLiterature(1, literature));
    }

    @Test
    @DisplayName("deleting literature if there is only one literature")
    void removeLiteraturetest2() {
        List<Literature> literature = new ArrayList<>();
        literature.add(new BookModel("Java", "Unknown", "Unknown", 2020));

        List<Literature> expected = new ArrayList<>();

        assertEquals(expected, literatureService.removeLiterature(1, literature));
    }

}