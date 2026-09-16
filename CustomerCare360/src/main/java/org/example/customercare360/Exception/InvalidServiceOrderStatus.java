package org.example.customercare360.Exception;

public class InvalidServiceOrderStatus extends RuntimeException {

    public InvalidServiceOrderStatus(String message) {
        super(message);
    }
}