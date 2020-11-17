package com.lms.spd.repository;


import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.Repository;

import java.util.ArrayList;
import java.util.List;

public class LiteratureRepository implements Repository {

    List<Literature> literatures = new ArrayList<>();

    public List<Literature> getAll() {
        return literatures;
    }

    public void setAll(List literatures) {
        this.literatures = literatures;
    }


}
