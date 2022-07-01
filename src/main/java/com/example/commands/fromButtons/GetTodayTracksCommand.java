package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import com.example.commands.base.Command;
import com.example.models.Track;
import com.example.services.withDB.TracksService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class GetTodayTracksCommand implements Command {
    private final TracksService tracksService;
    private final Bot bot;
    private final DeleteMessageService deleteMessageService;
    private final UpdateLastReceivedMessageService updateLastReceivedMessageService;

    public GetTodayTracksCommand(TracksService tracksService, Bot bot, DeleteMessageService deleteMessageService, UpdateLastReceivedMessageService updateLastReceivedMessageService) {
        this.tracksService = tracksService;
        this.bot = bot;
        this.deleteMessageService = deleteMessageService;
        this.updateLastReceivedMessageService = updateLastReceivedMessageService;
    }

    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        List<Track> tracks = tracksService.getTodayTracks(userId);

        List<List<InlineKeyboardButton>> overList = new ArrayList<>();
        for (Track track : tracks) {
            InlineKeyboardButton trackButton = new InlineKeyboardButton();
            trackButton.setText(track.getDescription() + " затрачено времени: " + track.getSpendHours());
            trackButton.setCallbackData("/remove_track "+ track.getId());

            List<InlineKeyboardButton> list = new ArrayList<>();
            list.add(trackButton);
            overList.add(list);
        }

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText("Назад");
        backButton.setCallbackData("/menu");
        List<InlineKeyboardButton> list = new ArrayList<>();
        list.add(backButton);
        overList.add(list);

        SendMessage sm = new SendMessage();
        sm.setReplyMarkup(new InlineKeyboardMarkup(overList));
        sm.setChatId(userId);
        sm.setText("Нажмите на трек, чтобы удалить");
        try {
            Message becameMessage =  bot.execute(sm);
            updateLastReceivedMessageService.update(userId,becameMessage.getMessageId());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
