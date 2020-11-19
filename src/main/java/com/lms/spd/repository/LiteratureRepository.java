package com.lms.spd.repository;

import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.LiteratureRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LiteratureRepository implements LiteratureRepositoryInterface {

    List<Literature> literatures = new ArrayList<>();

    public List<Literature> getAll() {
        return literatures;
    }

    public void setAll(List literatures) {
        this.literatures = literatures;
    }

    @Override
    public Literature addLiterature(Literature literature) {
        literature.setId(generateIdLit(literatures));
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

    public static int generateIdLit(List<Literature> literatures) {
        Optional<Integer> x = literatures.stream().map(Literature::getId).reduce(Integer::max);
        int rez = x.orElse(0);
        rez++;
        return rez;
    }
}
