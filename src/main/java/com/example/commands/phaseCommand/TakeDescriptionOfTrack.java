package com.example.commands.phaseCommand;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.models.BufferForCreatingTrackInFewSteps;
import com.example.services.SendMessageService;
import com.example.userStatementManager.Statements;
import com.example.userStatementManager.UserStatementManager;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TakeDescriptionOfTrack implements Command {
    private final SendMessageService sendMessageService;
    private final DeleteMessageService deleteMessageService;

    public TakeDescriptionOfTrack(SendMessageService sendMessageService, DeleteMessageService deleteMessageService) {
        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
    }
    @Override
    public void execute(Update update) {
    Long userId = Bot.getPlayerIdFromUpdate(update);
    deleteMessageService.deleteMessages(userId);
    String description = update.getMessage().getText();
        BufferForCreatingTrackInFewSteps.setBufferedTrackDescription(userId,description);
        sendMessageService.sendMessage(userId,"Введите потраченное время на эту задачу в часах\n" +
                                                      "Пример: 2");
        UserStatementManager.setStatement(userId, Statements.answeringAboutTime);
    }
}
