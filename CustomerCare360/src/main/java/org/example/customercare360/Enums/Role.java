package org.example.customercare360.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ADMIN,
    USER,
    AGENT,
    BILL_OPT;

    @JsonCreator
    public static Role fromValue(String value){
        return Role.valueOf(value.toUpperCase());
    }
}
