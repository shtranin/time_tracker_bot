package com.example.teamLeadsManager.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teamLeads")
public class TeamLead {
    @Id
    private Long telegramId;
    private boolean hasGroup;
    private String firstName;
    private String lastName;

    public boolean isHasGroup() {
        return hasGroup;
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

    public TeamLead(Long telegramId, String firstName,String lastName,boolean hasGroup) {
        this.telegramId = telegramId;
        this.hasGroup = hasGroup;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TeamLead() {
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long id) {
        this.telegramId = id;
    }

    public boolean hasGroup() {
        return hasGroup;
    }

    public void setHasGroup(boolean hasGroup) {
        this.hasGroup = hasGroup;
    }
}
