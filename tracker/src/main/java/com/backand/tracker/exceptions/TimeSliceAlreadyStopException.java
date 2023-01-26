package com.backand.tracker.exceptions;

public class TimeSliceAlreadyStopException extends RuntimeException {
    public TimeSliceAlreadyStopException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TimeSliceAlreadyStopException(String msg) {
        super(msg);
    }
}
