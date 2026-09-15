package org.example.customercare360.Exception;

import jakarta.persistence.ElementCollection;
import org.example.customercare360.DTO.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> InvalidToken(InvalidToken ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullCustomerType.class)
    public ResponseEntity<String> NullCustomerTYpe(NullCustomerType ex){
        return  ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(AgentNotFound.class)
    public ResponseEntity<String> AgentNotFound(AgentNotFound ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoServiceOrdersFound.class)
    public ResponseEntity<String> handleServiceOrderNotFound(NoServiceOrdersFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoAssignedOrdersFound.class)
    public ResponseEntity<String> handleNoAssignedOrders(NoAssignedOrdersFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderTypeNotFound.class)
    public ResponseEntity<String> handleOrderTypeNotFound(OrderTypeNotFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullCustomerType.class)
    public ResponseEntity<String> NullCustomerTYpe(NullCustomerType ex){
        return  ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotificationNotFound.class)
    public ResponseEntity<String> NotificationIdNotFound(NotificationNotFound ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ServiceAccountNotFound.class)
    public ResponseEntity<String> handleServiceAccountNotFound(ServiceAccountNotFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AgentNameNotFound.class)
    public ResponseEntity<String> handleAgentNameNotFound(AgentNameNotFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}