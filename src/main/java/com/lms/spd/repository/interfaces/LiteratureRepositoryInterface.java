package com.lms.spd.repository.interfaces;

import com.lms.spd.models.interfaces.Literature;

public interface LiteratureRepositoryInterface {

    Literature addLiterature(Literature entity);

    void removeLiterature(int id);

    void updateLiterature(Literature entity);
}
