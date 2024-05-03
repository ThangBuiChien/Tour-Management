package com.example.tourmanagement.repository;

import com.example.tourmanagement.model.Notification;
import com.example.tourmanagement.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(UserModel user);
}
