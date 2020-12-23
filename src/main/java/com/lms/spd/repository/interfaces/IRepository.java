package com.lms.spd.repository.interfaces;

import java.util.List;

public interface IRepository<T> extends AutoCloseable{

    List<T> getAll();

    T getItem(int id);

    void create(T item);

    void update(T item);

    void delete(int id);

    void save();
}
