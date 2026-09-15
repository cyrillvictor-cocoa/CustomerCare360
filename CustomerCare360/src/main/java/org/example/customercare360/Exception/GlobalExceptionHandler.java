package org.example.customercare360.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> HttpMessageNotReadable(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UserNameExists.class)
    public ResponseEntity<String> UserNameExists(UserNameExists ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailExists.class)
    public ResponseEntity<String> EmailExists(EmailExists ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> UserNameNotFound(UserNotFound ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordInValid.class)
    public ResponseEntity<String> PasswordInvalid(PasswordInValid ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity<String> InvalidToken(InvalidToken ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullCustomerType.class)
    public ResponseEntity<String> NullCustomerTYpe(NullCustomerType ex){
        return  ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NotificationNotFound.class)
    public ResponseEntity<String> NotificationIdNotFound(NotificationNotFound ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

}