package com.volhub.v1.Services.Notification;

import java.util.List;

import com.volhub.v1.Modals.Notification.NotificationModel;

public interface NotificationService {

    public NotificationModel createNotification(NotificationModel notification);

    public List<NotificationModel> VGetNotificationByid(String id);
    public List<NotificationModel> orgGetNotificationByid(String id);
    public String updateStatus(String id,String status);
    
}
