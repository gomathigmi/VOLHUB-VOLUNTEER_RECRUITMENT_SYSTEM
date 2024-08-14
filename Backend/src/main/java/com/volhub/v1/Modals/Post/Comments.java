package com.volhub.v1.Modals.Post;

import jakarta.persistence.Embeddable;

@Embeddable
public class Comments {
    private String username;
    private String img;
    private String comment;

    public Comments() {
    }

    public Comments(String username, String img, String comment) {
        this.username = username;
        this.img = img;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
