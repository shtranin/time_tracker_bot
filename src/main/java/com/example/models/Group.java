package com.example.models;

public class Group {
    private int id;
    private String color;
    private long teamLeadId;

    public int getId() {
        return id;
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
