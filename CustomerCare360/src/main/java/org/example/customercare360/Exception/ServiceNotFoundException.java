package org.example.customercare360.Exception;

public class ServiceNotFoundException
        extends RuntimeException {

    public ServiceNotFoundException(
            String message) {

        super(message);
    }
}