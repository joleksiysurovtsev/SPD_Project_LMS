package com.lms.spd.models.interfaces;

import com.lms.spd.enums.LiteratureType;

public interface Literature {
    String getTitle();

    void setTitle(String title);

    String getAuthor();

    void setAuthor(String author);

    LiteratureType getType();

    void setType(LiteratureType type);

    String print();

}
