package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.models.User;
import com.example.services.SendMessageService;
import com.example.teamLeadsManager.TeamLeadsManager;
import com.example.teamLeadsManager.model.TeamLead;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RegisterTeamLeadCommand implements Command {
    private final SendMessageService sendMessageService;
    private final DeleteMessageService deleteMessageService;
    private final TeamLeadsManager teamLeadsManager;

    public RegisterTeamLeadCommand(SendMessageService sendMessageService, DeleteMessageService deleteMessageService, TeamLeadsManager teamLeadsManager) {
        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
        this.teamLeadsManager = teamLeadsManager;
    }

    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        String[] data = update.getCallbackQuery().getData().split(" ");
        Long teamLeadId = Long.parseLong(data[1]);
        String firstName = data[2];
        String lastName = data[3];
        teamLeadsManager.save(new TeamLead(teamLeadId,firstName,lastName,false));
        Redirector.redirect("/create_teamlead",update);
        sendMessageService.sendMessage(userId,firstName + " " + lastName + " зарегистрирован как тимлид");

    }
}
