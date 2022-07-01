package com.example.commands.specificСommands;

import com.example.bot.Bot;

import com.example.clearing_old_messages.DeleteMessageService;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import com.example.commands.base.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class MenuCommand implements Command {
    private final Bot bot;
    private final UpdateLastReceivedMessageService updateLastReceivedMessageService;
    private final DeleteMessageService deleteMessageService;


    public MenuCommand(Bot bot, UpdateLastReceivedMessageService updateLastReceivedMessageService, DeleteMessageService deleteMessageService) {
        this.bot = bot;
        this.updateLastReceivedMessageService = updateLastReceivedMessageService;
        this.deleteMessageService = deleteMessageService;

    }

    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);


        InlineKeyboardButton trackButton = new InlineKeyboardButton();
        trackButton.setText("Внести новый трек");
        trackButton.setCallbackData("/create_track");
        List<InlineKeyboardButton> list1 = new ArrayList<>();
        list1.add(trackButton);

        InlineKeyboardButton todayTracksButton = new InlineKeyboardButton();
        todayTracksButton.setText("Треки за сегодня");
        todayTracksButton.setCallbackData("/get_tracks");
        list1.add(todayTracksButton);
        List<List<InlineKeyboardButton>> overList = new ArrayList<>();
        overList.add(list1);


        SendMessage sm = new SendMessage();
        sm.setText("Выберите действие");
        sm.setChatId(userId);
        sm.setReplyMarkup(new InlineKeyboardMarkup(overList));

        try {
            Message becameMessage = bot.execute(sm);
            updateLastReceivedMessageService.update(userId, becameMessage.getMessageId());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}
