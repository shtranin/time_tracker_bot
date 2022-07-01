package com.example.models;

import java.util.Date;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private Date lastTrack;

    public User(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastTrack = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastTrack() {
        return lastTrack;
    }

    public void setLastTrack(Date lastTrack) {
        this.lastTrack = lastTrack;
    }
}
