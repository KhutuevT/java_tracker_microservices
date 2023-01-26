package com.backand.tracker.exceptions;

import com.backand.tracker.exceptions.TimeSliceAlreadyStartException;
import com.backand.tracker.exceptions.TimeSliceAlreadyStopException;
import com.backand.tracker.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleExceptions(UserNotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleException(EntityNotFoundException e) {
        return  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TimeSliceAlreadyStartException.class)
    public ResponseEntity handleException(TimeSliceAlreadyStartException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TimeSliceAlreadyStopException.class)
    public ResponseEntity handleException(TimeSliceAlreadyStopException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
