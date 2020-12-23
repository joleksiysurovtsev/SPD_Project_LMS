package com.lms.spd.repository;

import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.IRepository;

import java.util.List;

public class SQLLiteratureRepository implements IRepository<Literature> {

    private LiteratureDaoImpl  literatureDaoImpl;

    @Override
    public List<Literature> getAll() {
        return null;
    }

    @Override
    public Literature getItem(int id) {
        Literature literature = literatureDaoImpl.read(id);
        return literature;
    }

    @Override
    public void create(Literature item) {

    }

    @Override
    public void update(Literature item) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void save() {

    }

    @Override
    public void close() throws Exception {

    }
}
