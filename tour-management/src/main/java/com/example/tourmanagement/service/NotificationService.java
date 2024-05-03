package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Notification;
import com.example.tourmanagement.model.UserModel;

import java.util.List;

public interface NotificationService {
    void createNotification(UserModel user, String message);
    List<Notification> getNotificationsForUser(UserModel user);
}
