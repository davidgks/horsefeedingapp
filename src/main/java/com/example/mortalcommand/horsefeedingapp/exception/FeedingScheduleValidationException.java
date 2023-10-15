package com.example.mortalcommand.horsefeedingapp.exception;

public class FeedingScheduleValidationException extends RuntimeException{
    public FeedingScheduleValidationException(String errorMessage) {
        super(errorMessage);
    }
}
