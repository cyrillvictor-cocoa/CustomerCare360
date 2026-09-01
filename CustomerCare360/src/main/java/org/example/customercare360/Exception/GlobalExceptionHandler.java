package org.example.customercare360.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(
            Exception ex) {

        return new ResponseEntity<>(
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class) //ResourceNotFoundException
    public ResponseEntity<String> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }



    @ExceptionHandler(IllegalArgumentException.class) //InvalidRequestException
    public ResponseEntity<String> handleInvalidRequestException(
            IllegalArgumentException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class) //DuplicateResourceException
    public ResponseEntity<String> handleDuplicateResourceException(
            org.springframework.dao.DuplicateKeyException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(IllegalStateException.class) // OperationNotAllowedException
    public ResponseEntity<String> handleOperationNotAllowedException(
            IllegalStateException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.CONFLICT
        );
    }




}