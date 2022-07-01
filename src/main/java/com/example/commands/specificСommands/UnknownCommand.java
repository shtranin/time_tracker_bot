package com.example.commands.specificСommands;


import com.example.bot.Bot;
import com.example.commands.base.Command;
import com.example.services.SendMessageService;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {
    private final SendMessageService service;
    private String command = "unknown command";
    private Bot bot;

    public UnknownCommand(SendMessageService service,Bot bot) {
        this.service = service;
        this.bot = bot;
    }


    @Override
    public void execute(Update update) {
        service.sendMessage(Bot.getPlayerIdFromUpdate(update),command);
    }
}
