package org.example.customercare360.Exception;

public class ServiceRequestNotFoundException extends RuntimeException {

    public ServiceRequestNotFoundException(String message) {
        super(message);
    }
}