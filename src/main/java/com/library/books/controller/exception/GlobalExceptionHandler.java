package com.library.books.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookISBNAlreadyExistException.class)
    public ResponseEntity<DetailedErrorResponse> handleBookISBNAlreadyExistException(BookISBNAlreadyExistException e,
                                                                                     WebRequest webRequest) {
        DetailedErrorResponse detailedErrorResponse = DetailedErrorResponse
                .builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookISBNMismatchException.class)
    public ResponseEntity<DetailedErrorResponse> handleBookISBNMismatchException(BookISBNMismatchException e,
                                                                                     WebRequest webRequest) {
        DetailedErrorResponse detailedErrorResponse = DetailedErrorResponse
                .builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookISBNNotFoundException.class)
    public ResponseEntity<DetailedErrorResponse> handleBookISBNNotFoundException(BookISBNNotFoundException e,
                                                                                     WebRequest webRequest) {
        DetailedErrorResponse detailedErrorResponse = DetailedErrorResponse
                .builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.NOT_FOUND)
                .errorMessage(e.getMessage())
                .timestamp(new Date())
                .build();
        return  new ResponseEntity<>(detailedErrorResponse, HttpStatus.NOT_FOUND);
    }


}
