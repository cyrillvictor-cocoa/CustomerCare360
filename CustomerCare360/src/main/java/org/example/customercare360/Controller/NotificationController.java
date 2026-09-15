package org.example.customercare360.Controller;

import org.example.customercare360.DTO.NotificationSendRequest;
import org.example.customercare360.Entity.Notification;
import org.example.customercare360.Services.DBNotificationService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final DBNotificationService dbNotificationService;

    NotificationController(DBNotificationService dbNotificationService){this.dbNotificationService = dbNotificationService;}

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getNotification(@PathVariable Integer userId){
        return ResponseEntity.ok(dbNotificationService.getNotification(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationSendRequest notificationSendRequest, Authentication authentication){
        return ResponseEntity.ok(dbNotificationService.createNotification(notificationSendRequest,authentication));
    }
}
