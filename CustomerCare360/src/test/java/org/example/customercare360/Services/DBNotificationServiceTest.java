package org.example.customercare360.Services;

import org.example.customercare360.DTO.NotificationSendRequest;
import org.example.customercare360.DTO.NotificationUpdateStatusRequest;
import org.example.customercare360.Entity.Notification;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.NotificationCategory;
import org.example.customercare360.Enums.NotificationStatus;
import org.example.customercare360.Exception.UserNotFound;
import org.example.customercare360.Repository.NotificationRepository;
import org.example.customercare360.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DBNotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private DBNotificationService dbNotificationService;
    @Test
    void createNotification_Success() {

        NotificationSendRequest request = new NotificationSendRequest();
        request.setCategory(NotificationCategory.valueOf("BILL"));
        request.setMessage("Test Notification");

        User user = new User();
        user.setUsername("victor");

        when(authentication.getPrincipal())
                .thenReturn("victor");

        when(userRepository.findByUsername("victor"))
                .thenReturn(Optional.of(user));

        Notification result =
                dbNotificationService.createNotification(
                        request,
                        authentication
                );

        assertNotNull(result);
        assertEquals(NotificationStatus.UNREAD, result.getStatus());
        assertEquals(NotificationCategory.BILL, result.getCategory());
        assertEquals("Test Notification", result.getMessage());
        assertEquals(user, result.getUser());

        verify(notificationRepository, times(1))
                .save(any(Notification.class));
    }

    @Test
    void createNotification_UserNotFound() {

        NotificationSendRequest request =
                new NotificationSendRequest();

        when(authentication.getPrincipal())
                .thenReturn("victor");

        when(userRepository.findByUsername("victor"))
                .thenReturn(Optional.empty());

        assertThrows(
                UserNotFound.class,
                () -> dbNotificationService.createNotification(
                        request,
                        authentication
                )
        );

        verify(notificationRepository, never())
                .save(any());
    }

    @Test
    void getNotification_ReturnsList() {

        Notification notification = new Notification();

        List<Notification> notifications =
                List.of(notification);

        when(notificationRepository.findAllByUserUserId(1))
                .thenReturn(notifications);

        List<Notification> result =
                dbNotificationService.getNotification(1);

        assertEquals(1, result.size());

        verify(notificationRepository)
                .findAllByUserUserId(1);
    }

    @Test
    void updateNotificationStatus_Success() {

        Notification notification = new Notification();
        notification.setStatus(NotificationStatus.UNREAD);

        NotificationUpdateStatusRequest request =
                new NotificationUpdateStatusRequest();

        request.setNotificationId(1);
        request.setStatus(NotificationStatus.READ);

        when(notificationRepository.findByNotificationId(1))
                .thenReturn(Optional.of(notification));

        dbNotificationService.UpdateNotificationStatus(request);

        assertEquals(
                NotificationStatus.READ,
                notification.getStatus()
        );
    }
}
