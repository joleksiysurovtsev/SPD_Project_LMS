package com.lms.spd.repository.interfaces;

import com.lms.spd.models.interfaces.Literature;

import java.io.IOException;
import java.util.List;

public interface LiteratureRepositoryInterface {


    List<Literature> getAll();

    void setAll(List<Literature> literature) throws IOException;
}
