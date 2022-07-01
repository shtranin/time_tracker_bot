package com.example.userStatementManager;

public enum Statements {

    choosingGroupName("/register_group"),
    describingTrack("/take_description"),
    answeringAboutTime("/take_time");

    private String name;

    Statements(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
