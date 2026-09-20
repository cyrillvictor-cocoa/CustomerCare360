package org.example.customercare360.Exception;

public class ServiceAccountNotFound extends RuntimeException {

    public ServiceAccountNotFound(String message) {
        super(message);
    }
}