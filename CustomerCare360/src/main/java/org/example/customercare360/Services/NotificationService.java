package org.example.customercare360.Services;

import org.example.customercare360.DTO.NotificationSendRequest;
import org.example.customercare360.DTO.NotificationUpdateStatusRequest;
import org.example.customercare360.Entity.Notification;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.NotificationCategory;
import org.example.customercare360.Enums.NotificationStatus;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NotificationService {

    public Notification createNotification(NotificationSendRequest request, Authentication authentication);

    public List<Notification> getNotification(Integer notificationId);

    public void UpdateNotificationStatus(NotificationUpdateStatusRequest request);

}
