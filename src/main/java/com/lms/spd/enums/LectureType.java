package com.lms.spd.enums;

import com.lms.spd.models.interfaces.Lecture;

public enum LectureType {
    OPEN,
    CLOSE;

    public static LectureType getValueByNumber(int order){
        if (order - 1 <= LectureType.values().length){
            return LectureType.values()[order - 1];
        }
        return null;
    }
}
