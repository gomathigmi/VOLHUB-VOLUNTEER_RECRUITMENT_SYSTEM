package com.volhub.v1.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.volhub.v1.Modals.Notification.NotificationModel;

import jakarta.transaction.Transactional;


public interface Notificationrepo extends JpaRepository<NotificationModel, Integer> {
    @Query("SELECT m FROM NotificationModel m WHERE m.volunteerid = :id")
    List<NotificationModel> vgetnotifications(@Param("id") String id);

    @Query("SELECT m FROM NotificationModel m WHERE m.organizerid = :id")
    List<NotificationModel> orggetnotifications(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("UPDATE NotificationModel m SET m.notifytype = :status WHERE m.id = :id")
    void updateStatus(@Param("id") String id, @Param("status") String status);
}
