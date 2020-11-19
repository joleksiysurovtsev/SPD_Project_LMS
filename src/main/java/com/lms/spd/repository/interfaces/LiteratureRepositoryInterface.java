package com.lms.spd.repository.interfaces;

import com.lms.spd.models.interfaces.Literature;

public interface LiteratureRepositoryInterface {
    void addLiterature(Literature entity);

    void removeLiterature(Literature entity);

    void updateLiterature(Literature entity);
}
