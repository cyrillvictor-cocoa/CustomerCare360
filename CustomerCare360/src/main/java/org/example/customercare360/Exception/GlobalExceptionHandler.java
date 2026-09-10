package org.example.customercare360.Exception;

import org.example.customercare360.DTO.AuthResponse;
import org.example.customercare360.Exception.ServiceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.example.customercare360.Exception.ServiceRequestNotFoundException;
import org.example.customercare360.Exception.ServiceOrderNotFoundException;
import org.example.customercare360.Exception.ServiceNotFoundException;

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

    @ExceptionHandler(UserNameNotFound.class)
    public ResponseEntity<String> UserNameNotFound(UserNameNotFound ex){
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
    public ResponseEntity<String> NullCustomerTYpe(NullCustomerType ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ServiceRequestNotFoundException.class)
    public ResponseEntity<String> handleServiceRequestNotFound(ServiceRequestNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceOrderNotFoundException.class)
    public ResponseEntity<String> handleServiceOrderNotFound(ServiceOrderNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<String>handleServiceNotFound(ServiceNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}

