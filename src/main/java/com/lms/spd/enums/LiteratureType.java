package com.lms.spd.enums;

import com.lms.spd.models.BookModel;

import java.awt.print.Book;

public enum LiteratureType {
    BOOK,
    JOURNAL_ARTICLE,
    INTERNET_ARTICLE;

    public static LiteratureType getValueByNumber(int order){
        return order - 1 <= LectureType.values().length ? LiteratureType.values()[order - 1] : null;
    }
}
