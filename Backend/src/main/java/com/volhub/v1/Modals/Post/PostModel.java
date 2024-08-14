package com.volhub.v1.Modals.Post;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String userid;
    String firbasename;
    String type;
    String time;

    @Column(columnDefinition = "TEXT")
    String url;

    @Column(columnDefinition = "TEXT")
    String msg;

    @ElementCollection
    List<String> likes;

    @ElementCollection
    List<Comments> comments;

    public PostModel() {
    }

    public PostModel(int id, String userid, String url, String type, List<String> likes, List<Comments> comments) {
        this.id = id;
        this.userid = userid;
        this.url = url;
        this.type = type;
        this.likes = likes;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirbasename() {
        return firbasename;
    }

    public void setFirbasename(String firbasename) {
        this.firbasename = firbasename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

   
}
