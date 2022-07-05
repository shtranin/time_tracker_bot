package com.example.models;

import java.util.Date;

public class Track {
    private Long id;
   // private Date date;
    private int spentHours;
    private String description;
    private Long userId;


    public Track() {
    }

    public Track(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public int getSpentHours() {
        return spentHours;
    }

    public void setSpentHours(int spentHours) {
        this.spentHours = spentHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
