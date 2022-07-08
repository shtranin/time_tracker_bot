package com.example.models;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "user", propOrder = {"telegramId", "firstName", "lastName", "lastModified"})
public class User {
    @XmlElement(name = "telegramId")
    private long telegramId;
    @XmlElement(name = "firstName")
    private String firstName;
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "lastModified")
    private String lastModified;

    public User(long telegramId, String firstName, String lastName) {
        this.telegramId = telegramId;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    @Override
    public String toString() {
        return "User{" +
                "telegramId=" + telegramId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastModified='" + lastModified + '\'' +
                '}';
    }

    public User() {
    }

    public long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(long telegramId) {
        this.telegramId = telegramId;
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

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
}
