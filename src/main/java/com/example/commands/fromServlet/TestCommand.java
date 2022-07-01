package com.example.commands.fromServlet;

import com.example.commands.base.Command;
import com.example.services.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TestCommand implements Command {
    private final SendMessageService sendMessageService;

    public TestCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {

    }
    public void execute(Long userId,String msg){
        sendMessageService.sendMessage(userId,msg);
    }
}
