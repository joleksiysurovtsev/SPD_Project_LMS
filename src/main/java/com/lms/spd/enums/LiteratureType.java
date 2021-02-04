package com.lms.spd.enums;

import java.util.Arrays;
import java.util.stream.Stream;

public enum LiteratureType {
    BOOK,
    JOURNAL_ARTICLE,
    INTERNET_ARTICLE;

    public static Stream<LiteratureType> stream() {
        return Arrays.stream(LiteratureType.values());
    }

    public static LiteratureType getValueByNumber(int order) {
        return order - 1 <= LectureType.values().length ? LiteratureType.values()[order - 1] : null;
    }

    public static String toListString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < LiteratureType.values().length; i++) {
            list.append(i + 1).append(". ").append(LiteratureType.values()[i]).append("\n");
        }
        return list.toString();
    }
}
