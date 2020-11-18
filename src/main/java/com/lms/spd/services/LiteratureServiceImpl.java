package com.lms.spd.services;

import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.interfaces.LiteratureService;

import java.util.ArrayList;
import java.util.List;

public class LiteratureServiceImpl implements LiteratureService {

    List<Literature> literatures = new ArrayList<>();

    @Override
    public List<Literature> getLiteratures() {
        return literatures;
    }

    @Override
    public void setLiteratures(List<Literature> literatures) {
        this.literatures = literatures;
    }

    @Override
    public List<Literature> addLiterature(Literature litAdded, List<Literature> lit) {
        lit.add(litAdded);
        return lit;
    }

    @Override
    public List<Literature> removeLiterature(int numberLit, List<Literature> lit) {
        if (lit.size() == 1) {
            lit = new ArrayList<>();
        } else {
            lit.remove(numberLit - 1);
        }
        return lit;
    }

}
