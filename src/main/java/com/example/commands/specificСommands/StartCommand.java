package com.example.commands.specificСommands;

import com.example.bot.Bot;
import com.example.bot.LectorId;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.services.withDB.UserService;
import com.example.services.SendMessageService;

import org.telegram.telegrambots.meta.api.objects.Update;



public class StartCommand implements Command {
    private final SendMessageService sendMessageService;
    private final UserService userService;


    public StartCommand(UserService userService, SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
        this.userService = userService;

    }


    @Override
    public void execute(Update update) {

        Long userId = Bot.getPlayerIdFromUpdate(update);

        if (userId.equals(LectorId.getId())) {
            Redirector.redirect("/admin", update);
        } else {
            String command = "Вы были успешно зарегистрированы";
            sendMessageService.sendMessage(userId, command);
            userService.registerUser(update);
        }
    }
}
