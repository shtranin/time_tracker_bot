package com.example.models;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.FIELD)
public class  Group {
    @XmlElement(name="id")
    private int id;
    @XmlElement(name="color")
    private String color;
    @XmlElement(name="teamLeadId")
    private long teamLeadId;

    public int getId() {
        return id;
    }

    public Group() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getTeamLeadId() {
        return teamLeadId;
    }

    public void setTeamLeadId(long teamLeadId) {
        this.teamLeadId = teamLeadId;
    }

    public Group(String color, long teamLeadId) {
        this.color = color;
        this.teamLeadId = teamLeadId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=net poka "  +
                ", color='" + color + '\'' +
                ", teamLeadId=" + teamLeadId +
                '}';
    }
}
