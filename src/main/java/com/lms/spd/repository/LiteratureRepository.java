package com.lms.spd.repository;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.LiteratureRepositoryInterface;
import com.lms.spd.repository.parsers.ParserLecturesJSON;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class LiteratureRepository implements LiteratureRepositoryInterface {


    List<Literature> literatures = new ArrayList<>() {
        {
            add(new BookModel("Ab", "Author", "Gentre", 1986, 1));
        }
    };


    public List<Literature> getAll() {
        return ParserLiteraturesJSON.parseLiteraturesFromJSON();
    }

    public void setAll(List<Literature> literatures) throws IOException {
        ParserLiteraturesJSON.parseLiteraturesInJSON(literatures);
    }

    @Override
    public Literature addLiterature(Literature literature) {
        literatures.add(literature);
        return literature;
    }

    @Override
    public void removeLiterature(int id) {
        literatures.removeIf(e -> e.getId() == id);
    }

    @Override
    public void updateLiterature(Literature entity) {

    }


}
