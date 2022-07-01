package com.example.commands.phaseCommand;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.models.BufferForCreatingTrackInFewSteps;
import com.example.models.Track;
import com.example.services.SendMessageService;
import com.example.services.withDB.TracksService;
import com.example.userStatementManager.UserStatementManager;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TakingSpentTimeForSolution implements Command {
    private final TracksService tracksService;
    private final SendMessageService sendMessageService;
    private final DeleteMessageService deleteMessageService;

    public TakingSpentTimeForSolution(TracksService tracksService, SendMessageService sendMessageService, DeleteMessageService deleteMessageService) {
        this.tracksService = tracksService;
        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
    }
    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        try {
            int spentHours = Integer.parseInt(update.getMessage().getText());
            BufferForCreatingTrackInFewSteps.setBufferForTrackSpentHours(userId,spentHours);
        }catch (Exception e){
            sendMessageService.sendMessage(userId,"Что то пошло не так, повторите попытку :)");
        }
        UserStatementManager.makeUserFree(userId);
        Track track = BufferForCreatingTrackInFewSteps.getTrackAndDeleteFromBuffed(userId);
        tracksService.sendTrack(track);
        Redirector.redirect("/menu",update);
        sendMessageService.sendMessage(userId,"Трек сохранен");

    }
}
