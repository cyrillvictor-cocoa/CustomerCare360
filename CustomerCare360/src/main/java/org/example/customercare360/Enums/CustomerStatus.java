package org.example.customercare360.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CustomerStatus {

    ACTIVE,
    INACTIVE;

    @JsonCreator
    public static CustomerStatus fromValue(String value) {
        return CustomerStatus.valueOf(
                value.toUpperCase());
    }
}
