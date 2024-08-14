package com.volhub.v1.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volhub.v1.Modals.Notification.NotificationModel;
import com.volhub.v1.Services.Notification.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin(origins = "*")
@RequestMapping("/notify")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    public NotificationModel postMethodName(@RequestBody NotificationModel notification) {

        return notificationService.createNotification(notification);
    }

    @GetMapping("/vol/get")
    public List<NotificationModel> getv_Notifications(@RequestParam String id) {
        return notificationService.VGetNotificationByid(id);
    }

    @GetMapping("/org/get")
    public List<NotificationModel> getorg_Notifications(@RequestParam String id) {
        return notificationService.orgGetNotificationByid(id);
    }

    @PutMapping("/status")
    public String putMethodName(@RequestParam String id, @RequestParam String status) {
        
        return notificationService.updateStatus(id,status);
    }

}
