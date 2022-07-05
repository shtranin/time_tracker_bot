package com.example.services.withDB;

import com.example.models.Group;
import com.example.soap.client.SoapClientForTeamService;


public class GroupService {

    public GroupService() {
    }

    public void sendGroup(Group group){
        new SoapClientForTeamService().createGroup(group);
    }
    public void removeGroupByTeamLeadId(Long userId){
        new SoapClientForTeamService().removeGroup(userId);
    }
    public void addUserInGroup(Long userId,int groupId){
       new SoapClientForTeamService().addUserInGroup(userId,groupId);
    }
    public Group getGroupIdByTeamLeadId(Long userId){
        return new SoapClientForTeamService().getGroupIdByUserId(userId);
    }
}
