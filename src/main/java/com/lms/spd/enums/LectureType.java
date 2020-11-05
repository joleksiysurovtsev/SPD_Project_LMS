package com.lms.spd.enums;

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
}
