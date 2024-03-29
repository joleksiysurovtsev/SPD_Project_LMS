package com.lms.spd.services.interfaces;

import java.util.*;

public interface IService<T> {

    List<T> getItems();

    T getSelectedItem();

    void setSelectedItem(int selected);

    T addItem(T lecture);

    boolean removeItems(int[] lectureRemove);

    T getByID(int id);
}
