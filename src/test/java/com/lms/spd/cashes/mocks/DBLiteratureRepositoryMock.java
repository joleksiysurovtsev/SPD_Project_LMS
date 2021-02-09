package com.lms.spd.cashes.mocks;

import com.lms.spd.models.BookModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.DBLiteratureRepository;

import java.util.*;

public class DBLiteratureRepositoryMock extends DBLiteratureRepository {

    private static List<Literature> literatureList = initLectLists();

    public DBLiteratureRepositoryMock() {
    }

    public static List<Literature> getLiteratureList() {
        return literatureList;
    }

    private static Map<Integer, Integer> initMap() {
        Map<Integer, Integer> intits = new HashMap<>();
        intits.put(1, 1);
        intits.put(1, 2);
        intits.put(1, 3);
        intits.put(2, 2);
        intits.put(3, 1);
        return intits;
    }

    private static List<Literature> initLectLists() {
        Calendar calendar = new GregorianCalendar(2020, 1, 16);
        List<Literature> literatures = new ArrayList<>();
        literatures.add(new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015));
        literatures.add(new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015));
        literatures.add(new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2020));
        return literatures;
    }

    @Override
    public List<Literature> readAll() {
        return literatureList;
    }

    @Override
    public Literature getByID(int id) {
        Optional<Literature> lecture1 = literatureList.stream().filter(lecture -> lecture.getId() == id).findFirst();
        return lecture1.orElse(new BookModel());
    }

    @Override
    public Literature create(Literature item) {
        literatureList.add(item);
        item.setId(4);
        return item;
    }

    @Override
    public boolean update(Literature item) {
        if (literatureList.stream().noneMatch(lecture -> lecture.getId() == item.getId())) {
            Optional<Literature> first = literatureList.stream().filter(literature -> literature.getId() == item.getId()).findFirst();
            int i = literatureList.indexOf(first.get());
            literatureList.set(i, item);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return literatureList.removeIf(literature -> literature.getId() == id);
    }

    @Override
    public List<Literature> literaturesBYLectureID(int id) {
        return super.literaturesBYLectureID(id);
    }

    @Override
    public int addIdMapToLiteratureToLeturesTable(int id, Integer integers) {
        return super.addIdMapToLiteratureToLeturesTable(id, integers);
    }

    @Override
    public void close() throws Exception {
        super.close();
    }

    public void updates() {
        Calendar calendar = new GregorianCalendar(2020, 1, 16);
        List<Literature> literatures = new ArrayList<>();
        literatures.add(new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015));
        literatures.add(new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2015));
        literatures.add(new BookModel("Философия Java", "Брюс Эккель", "Computer Science", 2020));
        literatureList = literatures;
    }
}
