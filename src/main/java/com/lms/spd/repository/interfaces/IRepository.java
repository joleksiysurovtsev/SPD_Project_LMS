package com.lms.spd.repository.interfaces;

import com.lms.spd.models.interfaces.Lecture;

import java.util.List;

public interface IRepository<T> extends AutoCloseable{

    List<T> readAll();

    T getByID(int id);

    T create(T item);

    boolean update(T item);

    boolean delete(int id);

    void addIdMapToLiteratureToLeturesTable(int id, List<Integer> integers);

    List<T> literaturesBYLectureID(int id);

}
