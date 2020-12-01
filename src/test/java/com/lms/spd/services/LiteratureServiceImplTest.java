package com.lms.spd.services;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;
import jdk.jfr.Description;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureServiceImplTest {
    private static File file = new File("src/test/resources/json/Literatures.json");

    @Test
    @Order(36)
    @Description("Literature is transferred to the input to the method and the list of references itself" +
            " to which you need to add the same literature, at the output we get the list of references " +
            "+ the one that was added")
    void addLiterature() {
        file.delete();
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        LiteratureServiceImpl literatureService = new LiteratureServiceImpl();
        //test List
        Literature book1 = new BookModel("Title", "Author", "Genre", 1986, 1);
        Literature book2 = new BookModel("Title", "Author", "Genre", 1986, 2);
        List<Literature> literatureList = new ArrayList<>();
        literatureList.add(book1);
        literatureList.add(book2);
        //the method is expected to return a sheet with added literature
        assertEquals(literatureList, literatureService.addLiterature(book2, literatureList));
        file.delete();
    }

    @Test
    @Order(37)
    @Description("The number of literature is passed to the input to the method and the list of references itself," +
            " at the output, we get a list without one literature")
    void removeLiterature() {
        file.delete();
        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
        LiteratureServiceImpl literatureService = new LiteratureServiceImpl();
        //test List
        List<Literature> literatureList = new ArrayList<>();
        Literature book1 = new BookModel("Title", "Author", "Genre", 1986, 2);
        Literature book2 = new BookModel("Title2", "Author", "Genre", 1986, 2);
        literatureList.add(book1);
        literatureList.add(book2);
        //expected list
        List<Literature> expected = new ArrayList<>();
        expected.add(book2);

        assertEquals(expected, literatureService.removeLiterature(1, literatureList));
        file.delete();
    }
}