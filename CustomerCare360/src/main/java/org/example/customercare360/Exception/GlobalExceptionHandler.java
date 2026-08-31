package org.example.customercare360.Exception;

import jakarta.persistence.ElementCollection;
import org.example.customercare360.DTO.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNameExists.class)
    public ResponseEntity<Response> UserNameExists(UserNameExists ex){
        return new ResponseEntity<>(new Response(ex.getMessage(),null),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailExists.class)
    public ResponseEntity<Response> EmailExists(EmailExists ex){
        return new ResponseEntity<>(new Response(ex.getMessage(),null),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNameNotFound.class)
    public ResponseEntity<Response> UserNameNotFound(UserNameNotFound ex){
        return new ResponseEntity<>(new Response(ex.getMessage(),null),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordInValid.class)
    public ResponseEntity<Response> PasswordInvalid(PasswordInValid ex){
        return new ResponseEntity<>(new Response(ex.getMessage(),null),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity<Response> InvalidToken(InvalidToken ex){
        return new ResponseEntity<>(new Response(ex.getMessage(),null),HttpStatus.UNAUTHORIZED);
    }



}