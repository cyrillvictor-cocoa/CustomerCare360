package org.example.customercare360.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum NotificationCategory {
    BILL,
    SERVICE,
    COMPLAINT;

    @JsonCreator
    public static NotificationCategory fromValue(String value){
        return NotificationCategory.valueOf(value.toUpperCase());
    }
}
