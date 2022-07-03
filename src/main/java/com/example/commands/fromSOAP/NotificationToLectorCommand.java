package com.example.commands.fromSOAP;

import com.example.models.User;
import com.example.services.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NotificationToLectorCommand {
    private final SendMessageService sendMessageService;
    public NotificationToLectorCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    public void execute(User user){

    }
}
