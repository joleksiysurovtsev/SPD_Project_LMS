package com.lms.spd.repository.interfaces;

import com.lms.spd.models.interfaces.Lecture;


import java.util.List;

public interface Repository<T> {

    List<T> getAll();

    void setAll(List<Lecture> ob);

}
