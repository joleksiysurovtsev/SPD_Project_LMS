package com.lms.spd.exceptions;

public class ValidateInputException extends Exception {
    public ValidateInputException() {
        super();
    }

    public ValidateInputException(String message) {
        super(message);
    }

    public ValidateInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateInputException(Throwable cause) {
        super(cause);
    }

}
