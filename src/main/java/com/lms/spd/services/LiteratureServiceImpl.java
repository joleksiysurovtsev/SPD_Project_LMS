package com.lms.spd.services;

import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.LiteratureRepository;
import com.lms.spd.services.interfaces.LiteratureService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LiteratureServiceImpl implements LiteratureService {

    private LiteratureRepository repository;

    public LiteratureServiceImpl() {
        this.repository = new LiteratureRepository();
    }

    @Override
    public List<Literature> addLiterature(Literature litAdded, List<Literature> lit) {
        litAdded.setId(generateIdLit(repository.getAll()));
        lit.add(litAdded);
        List<Literature> allLit = new ArrayList<>(repository.getAll());
        allLit.add(litAdded);
        repository.setAll(allLit);
        return lit;
    }

    @Override
    public List<Literature> removeLiterature(int numberLit, List<Literature> lit) {
        int id = lit.get(numberLit - 1).getId();
        List<Literature> allLit = new ArrayList<>(repository.getAll());
        allLit.removeIf(literature -> literature.getId() == id);
        repository.setAll(allLit);
        if (lit.size() == 1) {
            lit = new ArrayList<>();
        } else {
            lit.remove(numberLit - 1);
        }
        return lit;
    }

    public static int generateIdLit(List<Literature> literatures) {
        return (literatures.stream().map(Literature::getId).max(Integer::compareTo).orElse(0)) + 1;
    }
}
