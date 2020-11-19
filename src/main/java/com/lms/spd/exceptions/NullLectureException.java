package com.lms.spd.exceptions;

public class NullLectureException extends Exception {
    public NullLectureException() {
        super();
    }

    public NullLectureException(String message) {
        super(message);
    }

    public NullLectureException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullLectureException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString()
    {
        return "Error. You are trying to do something with a non-existent lecture.";
    }
}
