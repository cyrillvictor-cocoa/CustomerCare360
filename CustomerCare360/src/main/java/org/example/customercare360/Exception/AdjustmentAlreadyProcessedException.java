package org.example.customercare360.Exception;

public class AdjustmentAlreadyProcessedException
        extends RuntimeException {

    public AdjustmentAlreadyProcessedException(String message) {
        super(message);
    }
}
