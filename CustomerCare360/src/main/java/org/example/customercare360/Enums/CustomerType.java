package org.example.customercare360.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CustomerType {
    RESIDENTIAL,
    COMMERCIAL,
    INDUSTRIAL;

    @JsonCreator
    public static CustomerType fromvalue(String value){
        return CustomerType.valueOf(value.toUpperCase());
    }
}
