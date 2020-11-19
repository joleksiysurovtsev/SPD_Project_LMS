package com.lms.spd.services;

import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.LiteratureRepository;
import com.lms.spd.services.interfaces.LiteratureService;

import java.util.ArrayList;
import java.util.List;

public class LiteratureServiceImpl implements LiteratureService {

    private LiteratureRepository repository;

    public LiteratureServiceImpl() {
        this.repository = new LiteratureRepository();
    }

    @Override
    public List<Literature> addLiterature(Literature litAdded, List<Literature> lit) {
        lit.add(repository.addLiterature(litAdded));
        return lit;
    }

    @Override //переделать на удаление из репозитория и из лекции по айдишнику
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

}
