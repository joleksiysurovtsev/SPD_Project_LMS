package com.lms.spd.enums;

import java.util.Arrays;
import java.util.stream.Stream;

public enum LectureType {
    JAVA_CORE,
    JAVA_CONCURRENCY,
    DB,
    EE,
    COMMON,
    SOFT_SKILLS,
    TECH_SKILLS,
    CAREER;

    public static LectureType getValueByNumber(int order) {
        return order - 1 <= LectureType.values().length ? LectureType.values()[order - 1] : null;
    }

    public static Stream<LectureType> stream() {
        return Arrays.stream(LectureType.values());
    }

    public static String toListString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < LectureType.values().length; i++) {
            list.append(i + 1).append(". ").append(LectureType.values()[i]).append("\n");
        }
        return list.toString();
    }
}
