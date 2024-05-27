package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Notification;
import com.example.tourmanagement.model.UserModel;
import com.example.tourmanagement.observer.Observer;

import java.util.List;

public interface NotificationService extends Observer {
    void createNotification(UserModel user, String message);
    List<Notification> getNotificationsForUser(UserModel user);
}
