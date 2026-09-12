package org.example.customercare360.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.customercare360.Enums.NotificationStatus;

@Getter
@Setter
public class NotificationUpdateStatusRequest {

    private Integer notificationId;
    private NotificationStatus status;
}
