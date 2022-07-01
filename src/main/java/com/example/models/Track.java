package com.example.models;

import java.util.Date;

public class Track {
    private int id;

    public Track() {
    }

    public Track(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    private Date date;
    private int spendHours;
    private String description;
    private long userId;

    @Override
    public String toString() {
        return "Track{" +
                "id= ni" +
                ", date=" + date +
                ", spendHours=" + spendHours +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSpendHours() {
        return spendHours;
    }

    public void setSpendHours(int spendHours) {
        this.spendHours = spendHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
