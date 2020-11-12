package com.lms.spd.enums;

public enum LiteratureType {
    BOOK,
    JOURNAL_ARTICLE,
    INTERNET_ARTICLE,
    UNKNOWN;

    public static LiteratureType getValueByNumber(int order){
        return order - 1 <= LectureType.values().length ? LiteratureType.values()[order - 1] : null;
    }

    public static String toListString() {
        String list = "";
        for (int i = 0; i < LiteratureType.values().length; i++) {
            list += (i+1)+ ". " + LiteratureType.values()[i] +"\n";
        }
        return list;
    }
}
