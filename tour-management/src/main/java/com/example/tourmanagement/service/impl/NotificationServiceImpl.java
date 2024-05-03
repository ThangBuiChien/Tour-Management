package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Notification;
import com.example.tourmanagement.model.UserModel;
import com.example.tourmanagement.repository.NotificationRepo;
import com.example.tourmanagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepo notificationRepository;

    @Override
    public void createNotification(UserModel user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);

        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsForUser(UserModel user) {
        return notificationRepository.findByUser(user);
    }
}
