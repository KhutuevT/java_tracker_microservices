package com.backand.tracker.exceptions;

public class TimeSliceAlreadyStartException extends RuntimeException {
    public TimeSliceAlreadyStartException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TimeSliceAlreadyStartException(String msg) {
        super(msg);
    }
}
