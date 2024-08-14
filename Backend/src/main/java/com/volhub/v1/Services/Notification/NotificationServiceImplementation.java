package com.volhub.v1.Services.Notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volhub.v1.Modals.Notification.NotificationModel;
import com.volhub.v1.Repositories.Notificationrepo;

import jakarta.transaction.Transactional;

@Service
public class NotificationServiceImplementation implements NotificationService {

    @Autowired
    private Notificationrepo notificationrepo;

    public NotificationModel createNotification(NotificationModel notification) {
        return notificationrepo.save(notification);
    }

    public List<NotificationModel> VGetNotificationByid(String id) {
        return notificationrepo.vgetnotifications(id);
    }

    public List<NotificationModel> orgGetNotificationByid(String id) {
        return notificationrepo.orggetnotifications(id);
    }

    @Transactional
    public String updateStatus(String id, String status) {
        notificationrepo.updateStatus(id, status);
        return "Status updated successfully";
    }

}
