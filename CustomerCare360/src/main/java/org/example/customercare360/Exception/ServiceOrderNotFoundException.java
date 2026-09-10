package org.example.customercare360.Exception;

public class ServiceOrderNotFoundException extends RuntimeException {
    public ServiceOrderNotFoundException(String message) {
        super(message);
    }
}