package com.lms.spd.repository;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.LiteratureRepositoryInterface;
import java.util.ArrayList;

import java.util.List;

public class LiteratureRepository implements LiteratureRepositoryInterface {


    List<Literature> literatures = new ArrayList<>() {
        {
            add(new BookModel("Ab", "Author", "Gentre", 1986, 1));
        }
    };


    public List<Literature> getAll() {
        return literatures;
    }

    public void setAll(List<Literature> literatures) {
        this.literatures = literatures;
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
