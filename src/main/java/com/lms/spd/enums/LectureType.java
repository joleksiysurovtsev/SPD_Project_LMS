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


    public static LectureType getValueByNumber(int order){
        return order - 1 <= LectureType.values().length ? LectureType.values()[order - 1] : null;
    }

    public static Stream<LectureType> stream() {
        return Arrays.stream(LectureType.values());
    }

}
