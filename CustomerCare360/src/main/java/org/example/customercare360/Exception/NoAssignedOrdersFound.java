package org.example.customercare360.Exception;

public class NoAssignedOrdersFound extends RuntimeException {

    public NoAssignedOrdersFound(String message) {
        super(message);
    }
}