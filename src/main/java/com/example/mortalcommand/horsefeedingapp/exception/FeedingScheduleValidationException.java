package com.example.mortalcommand.horsefeedingapp.exception;

/**
 * Customized exception that is thrown when an unvalid feeding schedule is tried to be created.
 */
public class FeedingScheduleValidationException extends RuntimeException{

    /**
     * Constructor that constructs the exception.
     * @param errorMessage error message that can be provided to the constructor
     */
    public FeedingScheduleValidationException(String errorMessage) {
        super(errorMessage);
    }
}
