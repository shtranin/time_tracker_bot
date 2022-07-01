package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.models.User;
import com.example.services.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RegisterTeamLeadCommand implements Command {
    private SendMessageService sendMessageService;
    private DeleteMessageService deleteMessageService;

    public RegisterTeamLeadCommand(SendMessageService sendMessageService,DeleteMessageService deleteMessageService) {
        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
    }

    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        String[] data = update.getCallbackQuery().getData().split(" ");
        Long teamLeadId = Long.parseLong(data[1]);
        String firstName = data[2];
        String lastName = data[3];
        Bot.getTeamLeads().add(new User(teamLeadId,firstName,lastName));
        Redirector.redirect("/admin",update);
        sendMessageService.sendMessage(userId,firstName + " " + lastName + " зарегистрирован как тимлид");
        //TODO add teamlead to mongo
    }
}
