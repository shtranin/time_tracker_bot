package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.models.User;
import com.example.services.SendMessageService;
import com.example.services.withDB.GroupService;
import com.example.teamLeadsManager.TeamLeadsManager;
import com.example.teamLeadsManager.model.TeamLead;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class RemoveTeamLeadCommand implements Command {
    private final GroupService groupService;
    private final SendMessageService sendMessageService;
    private final DeleteMessageService deleteMessageService;
    private final TeamLeadsManager teamLeadsManager;

    public RemoveTeamLeadCommand(GroupService groupService, SendMessageService sendMessageService, DeleteMessageService deleteMessageService, TeamLeadsManager teamLeadsManager) {
        this.groupService = groupService;
        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
        this.teamLeadsManager = teamLeadsManager;
    }
    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        Long teamLeadId = Long.parseLong(update.getCallbackQuery().getData().split(" ")[1]);

        //List<User> teamLeads = Bot.getTeamLeads();
        //  List<TeamLead> teamLeads = teamLeadsManager.findAll();
//        User teamLeadForDeleting = null;
//        for(User user : teamLeads){
//            if(user.getTelegramId() == teamLeadId){
//                teamLeadForDeleting = user;
//                break;
//            }
//        }
      //  teamLeads.remove(teamLeadForDeleting);
        teamLeadsManager.deleteById(teamLeadId);
        groupService.removeGroupByTeamLeadId(userId);
        Redirector.redirect("/delete_teamlead",update);
        sendMessageService.sendMessage(userId,"Тимлид удален");
    }
}
