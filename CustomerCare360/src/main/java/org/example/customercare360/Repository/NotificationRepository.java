package org.example.customercare360.Repository;

import org.example.customercare360.Entity.Notification;
import org.example.customercare360.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {

    Optional<Notification> findByNotificationId(Integer notificationId);

    List<Notification> findAllByUserUserId(Integer userId);
}
