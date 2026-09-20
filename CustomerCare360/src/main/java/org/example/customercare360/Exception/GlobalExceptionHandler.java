package org.example.customercare360.Exception;


import org.example.customercare360.DTO.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> HttpMessageNotReadable(
            HttpMessageNotReadableException ex) {

        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserNameExists.class)
    public ResponseEntity<String> UserNameExists(
            UserNameExists ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.CONFLICT);
    }


    @ExceptionHandler(EmailExists.class)
    public ResponseEntity<String> EmailExists(
            EmailExists ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> UserNameNotFound(
            UserNotFound ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordInValid.class)
    public ResponseEntity<String> PasswordInvalid(
            PasswordInValid ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity<String> InvalidToken(
            InvalidToken ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullCustomerType.class)
    public ResponseEntity<String> NullCustomerTYpe(NullCustomerType ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ServiceRequestNotFoundException.class)
    public ResponseEntity<String> handleServiceRequestNotFound(ServiceRequestNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AgentNotFound.class)
    public ResponseEntity<String> AgentNotFound(AgentNotFound ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoServiceOrdersFound.class)
    public ResponseEntity<String> handleServiceOrderNotFound(NoServiceOrdersFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(OrderTypeNotFound.class)
    public ResponseEntity<String> handleOrderTypeNotFound(OrderTypeNotFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceOrderNotFoundException.class)
    public ResponseEntity<String> handleServiceOrderNotFound(ServiceOrderNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<String>handleServiceNotFound(ServiceNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String>
    handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex) {

        return new ResponseEntity<>(
                "Invalid ID format. ID must be a number.",
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(AdjustmentAlreadyProcessedException.class)
    public ResponseEntity<ApiErrorResponse>
    handleAdjustmentAlreadyProcessedException(
            AdjustmentAlreadyProcessedException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        ex.getMessage()));
    }






    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> ResourceNotFound(
            ResourceNotFoundException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }
}