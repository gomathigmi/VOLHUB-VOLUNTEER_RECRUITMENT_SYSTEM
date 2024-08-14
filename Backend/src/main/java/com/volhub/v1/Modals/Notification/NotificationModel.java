package com.volhub.v1.Modals.Notification;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String volunteerid;
    private String organizerid;
    private String applicationid;
    

    private String notifytype;
    private String notifycontent;

    public NotificationModel() {
    }
    public NotificationModel(int id, String volunteerid, String organizerid, String notifytype, String notifycontent , String applicationid) {
        this.id = id;
        this.volunteerid = volunteerid;
        this.organizerid = organizerid;
        this.notifytype = notifytype;
        this.applicationid = applicationid;
        this.notifycontent = notifycontent;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
  

    public String getNotifytype() {
        return notifytype;
    }
    public void setNotifytype(String notifytype) {
        this.notifytype = notifytype;
    }
    public String getNotifycontent() {
        return notifycontent;
    }
    public void setNotifycontent(String notifycontent) {
        this.notifycontent = notifycontent;
    }
    

    public String getVolunteerid() {
        return volunteerid;
    }

    public void setVolunteerid(String volunteerid) {
        this.volunteerid = volunteerid;
    }
    public String getOrganizerid() {
        return organizerid;
    }
    public void setOrganizerid(String organizerid) {
        this.organizerid = organizerid;
    }
    public String getApplicationid() {
        return applicationid;
    }
    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid;
    }
    
    
}
