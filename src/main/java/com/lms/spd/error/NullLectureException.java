package com.lms.spd.error;

public class NullLectureException extends Exception {

    @Override
    public String toString() {
        return "Error. You are trying to do something with a non-existent lecture.";
    }
}
