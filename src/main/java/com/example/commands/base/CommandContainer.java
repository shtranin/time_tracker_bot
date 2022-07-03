package com.example.commands.base;


import com.example.commands.fromButtons.*;
import com.example.commands.fromButtons.RegisterGroupCommand;
import com.example.commands.fromButtons.RemoveGroupCommand;
import com.example.commands.fromButtons.RemoveTeamLeadCommand;
import com.example.commands.fromButtons.RemoveTrackCommand;
import com.example.commands.fromSOAP.TestCommand;
import com.example.commands.phaseCommand.TakeDescriptionOfTrack;
import com.example.commands.phaseCommand.TakingSpentTimeForSolution;
import com.example.commands.specificСommands.AdminCommand;
import com.example.commands.specificСommands.MenuCommand;
import com.example.commands.specificСommands.StartCommand;
import com.example.commands.specificСommands.UnknownCommand;

import java.util.HashMap;


public class CommandContainer {

    private final HashMap<String,Command> container;
    private final UnknownCommand unknownCommand;


    {
        container = new HashMap<>();

    }



    public CommandContainer(UnknownCommand unknownCommand, StartCommand startCommand, MenuCommand menuCommand, AdminCommand adminCommand, DeleteGroupCommand deleteGroupCommand,
                            TestCommand testCommand, CreateTeamLeadCommand createTeamLeadCommand, RegisterTeamLeadCommand registerTeamLeadCommand, RemoveTeamLeadCommand removeTeamLeadCommand,
                            RemoveGroupCommand removeGroupCommand, CreateGroupCommand createGroupCommand, DeleteTeamLeadCommand deleteTeamLeadCommand, RegisterGroupCommand registerGroupCommand,
                            CreateTrackCommand createTrackCommand, TakeDescriptionOfTrack takeDescriptionOfTrack, TakingSpentTimeForSolution takingSpentTimeForSolution,
                            GetTodayTracksCommand getTodayTracksCommand, RemoveTrackCommand removeTrackCommand,AddDeveloperCommand addDeveloperCommand,RegisterDeveloperInGroup registerDeveloperInGroup) {
        this.unknownCommand = unknownCommand;
        container.put("/start",startCommand);
        container.put("/menu",menuCommand);
        container.put("/admin",adminCommand);
        container.put("/delete_group",deleteGroupCommand);
        container.put("/test",testCommand);
        container.put("/create_teamlead",createTeamLeadCommand);
        container.put(("/register_teamlead"),registerTeamLeadCommand);
        container.put("/remove_teamlead",removeTeamLeadCommand);
        container.put("/remove_group",removeGroupCommand);
        container.put("/create_group",createGroupCommand);
        container.put("/delete_teamlead",deleteTeamLeadCommand);
        container.put("/register_group",registerGroupCommand);
        container.put("/create_track",createTrackCommand);
        container.put("/take_description",takeDescriptionOfTrack);
        container.put("/take_time",takingSpentTimeForSolution);
        container.put("/get_tracks",getTodayTracksCommand);
        container.put("/remove_track",removeTrackCommand);
        container.put("/add_dev",addDeveloperCommand);
        container.put("/register_dev",registerDeveloperInGroup);

    }

    public Command receiveCommand(String command){
        return container.getOrDefault(command,unknownCommand);
    }

}
