package org.example.customercare360.Exception;


public class NoServiceOrdersFound extends RuntimeException {

    public NoServiceOrdersFound(String message) {
        super(message);
    }
}