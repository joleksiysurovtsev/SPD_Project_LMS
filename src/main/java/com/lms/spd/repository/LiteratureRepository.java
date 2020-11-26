package com.lms.spd.repository;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.LiteratureRepositoryInterface;
import com.lms.spd.repository.parsers.ParserLiteraturesJSON;

import java.io.IOException;


import java.util.List;

public class LiteratureRepository implements LiteratureRepositoryInterface {

    public List<Literature> getAll() {
        return ParserLiteraturesJSON.parseLiteraturesFromJSON();
    }

    public void setAll(List<Literature> literatures) throws IOException {
        ParserLiteraturesJSON.parseLiteraturesInJSON(literatures);
    }

}
