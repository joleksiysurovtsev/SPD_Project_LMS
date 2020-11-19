package com.lms.spd.repository;

import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.LiteratureRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class LiteratureRepository implements LiteratureRepositoryInterface {

    List<Literature> literatures = new ArrayList<>();


    @Override
    public void addLiterature(Literature entity) {
    }

    @Override
    public void removeLiterature(Literature entity) {
    }

    @Override
    public void updateLiterature(Literature entity) {

    }
}
