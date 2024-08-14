package com.volhub.v1.Modals.Applications;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(columnDefinition = "TEXT")
    private String url;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String organizerid;
    
    
    public Application() {
    }

    
    public Application(int id, String url, String description, String organizerid) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.organizerid = organizerid;
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

    

}
