package org.example.customercare360.DTO;


import lombok.Getter;
import lombok.Setter;
import org.example.customercare360.Entity.Notification;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.NotificationCategory;

@Getter
@Setter
public class NotificationSendRequest {

    private NotificationCategory category;
    private String message;
}
