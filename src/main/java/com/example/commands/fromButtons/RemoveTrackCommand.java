package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.services.SendMessageService;
import com.example.services.withDB.TracksService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RemoveTrackCommand implements Command {
    private final SendMessageService sendMessageService;
    private final DeleteMessageService deleteMessageService;
    private final TracksService tracksService;

    public RemoveTrackCommand(SendMessageService sendMessageService, DeleteMessageService deleteMessageService, TracksService tracksService) {

        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
        this.tracksService = tracksService;
    }
    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        int trackId = Integer.parseInt(update.getCallbackQuery().getData().split(" ")[1]);
        tracksService.removeTrackById(trackId);

        Redirector.redirect("/get_tracks",update);


    }
}
