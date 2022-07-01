package com.example.commands.fromButtons;


import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.services.SendMessageService;
import com.example.userStatementManager.Statements;
import com.example.userStatementManager.UserStatementManager;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CreateGroupCommand implements Command {
    private final SendMessageService sendMessageService;
    private final DeleteMessageService deleteMessageService;

    public CreateGroupCommand(SendMessageService sendMessageService, DeleteMessageService deleteMessageService) {
        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
    }

    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        sendMessageService.sendMessage(userId,"Создатель группы автоматически становится ее тимлидом\n" +
                                                      "Введите пожалуйста цвет Вашей группы в качестве названия");

        UserStatementManager.setStatement(userId, Statements.choosingGroupName);

    }
}
