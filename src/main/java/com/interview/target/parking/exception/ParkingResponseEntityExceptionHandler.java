package com.interview.target.parking.exception;

import com.interview.target.parking.model.error.ErrorResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ParkingResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParkingSlotsFullException.class)
    public ResponseEntity<ErrorResponseEntity> parkingSlotsFullException(final ParkingSlotsFullException e) {
        return this.generateErrorResponseEntity(e.getMessage(), "P001", HttpStatus.OK);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponseEntity> invalidTokenException(final InvalidTokenException e) {
        return this.generateErrorResponseEntity(e.getMessage(), "P002", HttpStatus.OK);
    }

    @ExceptionHandler(ParkingSearchException.class)
    public ResponseEntity<ErrorResponseEntity> parkingSearchException(final ParkingSearchException e) {
        return this.generateErrorResponseEntity(e.getMessage(), "P003", HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseEntity> invalidTokenException(final Exception e) {
        return this.generateErrorResponseEntity("Sorry!! There seems to be an hiccup in the app.", "P008", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private ResponseEntity<ErrorResponseEntity> generateErrorResponseEntity(String errorDesc, String errorCode, HttpStatus status) {
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity();
        errorResponseEntity.setErrorCode(errorCode);
        errorResponseEntity.setErrorDescription(errorDesc);
        return new ResponseEntity<>(errorResponseEntity, status);
    }
}