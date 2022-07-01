package com.example.services;


import com.example.bot.Bot;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SendMessageService{

    private Bot bot;
    private UpdateLastReceivedMessageService updateLastReceivedMessageService;

    public SendMessageService(Bot bot, UpdateLastReceivedMessageService updateLastReceivedMessageService) {
        this.bot = bot;
        this.updateLastReceivedMessageService = updateLastReceivedMessageService;
    }



    public void sendMessage(Long userId,String message) {
        SendMessage sm = new SendMessage();
        sm.setChatId(userId.toString());
        sm.setText(message);
        try {
            Message becameMessage =  bot.execute(sm);
            updateLastReceivedMessageService.update(userId,becameMessage.getMessageId());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
