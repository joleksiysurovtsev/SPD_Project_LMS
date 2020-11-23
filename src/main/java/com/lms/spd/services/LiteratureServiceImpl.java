package com.lms.spd.services;

import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.LiteratureRepository;
import com.lms.spd.services.interfaces.LiteratureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LiteratureServiceImpl implements LiteratureService {

    List<Literature> literatures = new ArrayList<>();

    private LiteratureRepository repository;

    public LiteratureServiceImpl() {
        this.repository = new LiteratureRepository();
    }

    @Override
    public List<Literature> addLiterature(Literature litAdded, List<Literature> lit) {
        lit.add(litAdded);
        return lit;
    }

    @Override
    public List<Literature> removeLiterature(int numberLit, List<Literature> lit) {
        int id = lit.get(numberLit-1).getId();
        repository.removeLiterature(id);
        if (lit.size() == 1) {
            lit = new ArrayList<>();
        } else {
            lit.remove(numberLit - 1);
        }
        return lit;
    }

    public static int generateIdLit(List<Literature> literatures) {
        Optional<Integer> x = literatures.stream().map(Literature::getId).reduce(Integer::max);
        int rez = x.orElse(0);
        rez++;
        return rez;
    }
}
