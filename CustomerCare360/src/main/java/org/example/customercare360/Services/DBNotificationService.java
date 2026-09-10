package org.example.customercare360.Services;

import org.example.customercare360.DTO.NotificationSendRequest;
import org.example.customercare360.DTO.NotificationUpdateStatusRequest;
import org.example.customercare360.Entity.Notification;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.NotificationStatus;
import org.example.customercare360.Exception.NotificationNotFound;
import org.example.customercare360.Exception.UserNotFound;
import org.example.customercare360.Repository.NotificationRepository;
import org.example.customercare360.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DBNotificationService implements NotificationService{

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    DBNotificationService(NotificationRepository notificationRepository,UserRepository userRepository){
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Notification createNotification(NotificationSendRequest request, Authentication authentication) {
        String username = (String)authentication.getPrincipal();
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFound("User not found with username "+ username));
        Notification notification = new Notification();
        notification.setCreatedDate(LocalDateTime.now());
        notification.setStatus(NotificationStatus.UNREAD);
        notification.setCategory(request.getCategory());
        notification.setMessage(request.getMessage());
        notification.setUser(user);

        notificationRepository.save(notification);
        return notification;

    }

    @Override
    public List<Notification> getNotification(Integer userId) {
        return notificationRepository.findAllByUserUserId(userId);
    }

    @Override
    public void UpdateNotificationStatus(NotificationUpdateStatusRequest request)throws NotificationNotFound {
        Notification notification = notificationRepository.findByNotificationId(request.getNotificationId()).orElseThrow(()->new NotificationNotFound("There is no notification with id "+request.getNotificationId()));
        notification.setStatus(request.getStatus());
    }

}
