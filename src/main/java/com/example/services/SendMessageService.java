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
            int becameMessageId = send(userId,message);
            updateLastReceivedMessageService.update(userId,becameMessageId);
    }
    public void sendMessage(Long userId,String message,boolean updateLastMessageId){
        send(userId,message);
    }
    private int send(Long userId, String message){
        SendMessage sm = new SendMessage();
        sm.setChatId(userId.toString());
        sm.setText(message);
        Message becameMessage = null;
        try {
            becameMessage =  bot.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return becameMessage.getMessageId();
    }
}
