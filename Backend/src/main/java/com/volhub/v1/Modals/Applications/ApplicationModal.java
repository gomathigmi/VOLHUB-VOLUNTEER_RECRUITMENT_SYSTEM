package com.volhub.v1.Modals.Applications;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "applications")
public class ApplicationModal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String url;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String organizerid;
    private String eventname;
    private String brouche;
    private String time;

    @ElementCollection  
    private List<String> volunteers;


    

    public ApplicationModal() {
    }

    public ApplicationModal(int id, String url, String description, String organizerid, String eventname,
            String brouche, String time, List<String> volunteers) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.organizerid = organizerid;
        this.eventname = eventname;
        this.brouche = brouche;
        this.time = time;
        this.volunteers = volunteers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizerid() {
        return organizerid;
    }

    public void setOrganizerid(String organizerid) {
        this.organizerid = organizerid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getBrouche() {
        return brouche;
    }

    public void setBrouche(String brouche) {
        this.brouche = brouche;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<String> volunteers) {
        this.volunteers = volunteers;
    }

   
}
