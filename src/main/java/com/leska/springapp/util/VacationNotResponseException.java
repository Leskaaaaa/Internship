package com.leska.springapp.util;

public class VacationNotResponseException extends RuntimeException {
    public VacationNotResponseException(String errorsMessage) {
        super(errorsMessage);
    }
}
