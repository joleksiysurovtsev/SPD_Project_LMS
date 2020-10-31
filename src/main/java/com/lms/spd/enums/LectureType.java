package com.lms.spd.enums;

public enum LectureType {
    OPEN,
    CLOSE;

    public static LectureType getValueByNumber(int order){
        return order - 1 <= LectureType.values().length ? LectureType.values()[order - 1] : null;
    }
}
