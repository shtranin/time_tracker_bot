package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import com.example.commands.base.Command;
import com.example.models.Group;

import com.example.services.SendMessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class DeleteGroupCommand implements Command {
    private final Bot bot;
    private final DeleteMessageService deleteMessageService;
    private final UpdateLastReceivedMessageService updateLastReceivedMessageService;

    public DeleteGroupCommand(Bot bot, DeleteMessageService deleteMessageService, UpdateLastReceivedMessageService updateLastReceivedMessageService) {
        this.bot = bot;
        this.deleteMessageService = deleteMessageService;
        this.updateLastReceivedMessageService = updateLastReceivedMessageService;
    }

    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);




            InlineKeyboardButton acceptButton = new InlineKeyboardButton();
            acceptButton.setText("Подтверждаю");
            acceptButton.setCallbackData("/remove_group " + userId);

            InlineKeyboardButton cancelButton = new InlineKeyboardButton();
            cancelButton.setText("Отмена");
            cancelButton.setCallbackData("/admin");


            List<InlineKeyboardButton> list = new ArrayList<>();
            list.add(acceptButton);
            list.add(cancelButton);

            List<List<InlineKeyboardButton>> overList = new ArrayList<>();
            overList.add(list);

            SendMessage sm = new SendMessage();
            sm.setReplyMarkup(new InlineKeyboardMarkup(overList));
            sm.setChatId(userId);
            sm.setText("Ваша группа будет удалена БЕЗВОЗВРАТНО");
            try {
               Message becameMessage = bot.execute(sm);
                updateLastReceivedMessageService.update(userId, becameMessage.getMessageId());
            } catch (TelegramApiException e) {
                e.printStackTrace();
                System.out.println("exception hz");
            }
        }
}

