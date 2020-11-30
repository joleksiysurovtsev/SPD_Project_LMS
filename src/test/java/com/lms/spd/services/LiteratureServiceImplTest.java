package com.lms.spd.services;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureServiceImplTest {
//    private static File file = new File("src/test/resources/json/Lectures.json");
//    private  static File file2 = new File("src/test/resources/json/Literatures.json");
//
//
//    @Test
//    void addLiterature() throws IOException {
//        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
//        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
//        file2.delete();
//        file.delete();
//
//        Literature book2 = new BookModel("Title", "Author", "Genre", 1986, 2);
//
//        List<Literature> literatureList = new ArrayList<>();
//        List<Literature> expLiteratureList = new ArrayList<>();
//
//        LiteratureServiceImpl literatureService = new LiteratureServiceImpl();
//
//        literatureService.addLiterature(book2, literatureList);
//
//        expLiteratureList.add(book2);
//        assertEquals(expLiteratureList, ParserLiteraturesJSON.parseLiteraturesFromJSON());
//        file2.delete();
//        file.delete();
//    }
//
//    @Test
//    void removeLiterature() throws IOException {
//        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
//        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
//        file2.delete();
//        file.delete();
//
//        LiteratureServiceImpl literatureService = new LiteratureServiceImpl();
//        //создали две книги
//        Literature book1 = new BookModel("Title", "Author", "Genre", 1986, 2);
//        Literature book2 = new BookModel("Title2", "Author", "Genre", 1986, 2);
//
//        //ожидаемый лист
//        List<Literature> literatureList = new ArrayList<>();
//        literatureList.add(book1);
//
//
//        literatureService.addLiterature(book1, ParserLiteraturesJSON.parseLiteraturesFromJSON());
//        literatureService.addLiterature(book2, ParserLiteraturesJSON.parseLiteraturesFromJSON());
//
//        literatureService.removeLiterature(2, ParserLiteraturesJSON.parseLiteraturesFromJSON());
//
//        assertEquals(literatureList, ParserLiteraturesJSON.parseLiteraturesFromJSON());
//        file2.delete();
//        file.delete();
//    }
//
//    @Test
//    void removeLiterature2(){
//        ParserLecturesJSON.seturl("src/test/resources/json/Lectures.json");
//        ParserLiteraturesJSON.seturl("src/test/resources/json/Literatures.json");
//        file2.delete();
//        file.delete();
//        LiteratureServiceImpl literatureService = new LiteratureServiceImpl();
//        //создали две книги
//        Literature book2 = new BookModel("Title2", "Author", "Genre", 1986, 1);
//
//        try {
//            literatureService.addLiterature(book2, ParserLiteraturesJSON.parseLiteraturesFromJSON());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            literatureService.removeLiterature(1, ParserLiteraturesJSON.parseLiteraturesFromJSON());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        assertEquals(new ArrayList<>(), ParserLiteraturesJSON.parseLiteraturesFromJSON());
//        file2.delete();
//        file.delete();
//    }
}