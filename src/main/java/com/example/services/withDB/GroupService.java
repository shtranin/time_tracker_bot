package com.example.services.withDB;

import com.example.models.Group;

public class GroupService {

    public void sendGroup(Group group){
        //TODO create new group in db (Group)
        System.out.println("group added");
    }
    public void removeGroupByTeamLeadId(Long userId){
        //TODO remove group by TeamLeadId (Long teamleadId)
        System.out.println("group removed");
    }
    public void addUserInGroup(Long userId,int groupId){
        //TODO add user in group (Long userId,int groupId)
        System.out.println("user added in group");
    }
}
