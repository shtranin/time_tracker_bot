package com.example.commands.fromSOAP;

import com.example.bot.Bot;
import com.example.commands.base.Command;
import com.example.models.User;
import com.example.services.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TestCommand implements Command {
    private final SendMessageService sendMessageService;

    public TestCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
                Long userId = Bot.getPlayerIdFromUpdate(update);
                sendMessageService.sendMessage(userId,"priv epta");
    }
    public void execute(User[] users){
        for (int i = 0; i < users.length ; i++) {
            sendMessageService.sendMessage(325533383L, users[i].toString());
        }
    }
}
