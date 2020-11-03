package com.lms.spd.enums;

public enum LiteratureType {
    BOOK,
    JOURNAL_ARTICLE,
    INTERNET_ARTICLE,
    UNKNOWN;

    public static LiteratureType getValueByNumber(int order){
        return order - 1 <= LectureType.values().length ? LiteratureType.values()[order - 1] : null;
    }
}
