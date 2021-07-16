package com.stoom.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.DateFormat;
import java.util.Date;

/**
 * Class responsible for handle exceptions occurred in API.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Method responsible for handle {@code ObjectNotFoundException}.
     *
     * @param e       Exception occurred.
     * @param request HTPP Request performed.
     * @return A ResponseEntity<StandardException> with information about the exception.
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardException> objectNotFound(ObjectNotFoundException e) {
        long timeStamp = System.currentTimeMillis();
        String date = DateFormat.getDateTimeInstance().format(new Date(timeStamp));

        StandardException standardException = new StandardException(HttpStatus.NOT_FOUND.value(), date, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardException);
    }
}