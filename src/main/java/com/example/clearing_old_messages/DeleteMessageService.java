package com.example.clearing_old_messages;

import com.example.bot.Bot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Set;

public class DeleteMessageService {
    private Bot bot;
    private LastMessageRepository lastMessageRepository;


    public DeleteMessageService(Bot bot,LastMessageRepository lastMessageRepository) {
        this.bot = bot;
        this.lastMessageRepository = lastMessageRepository;
    }


    public void deleteMessages(Long userId) {
        if(DeletingBlocker.isBlocked(userId)){
            return;
        }
        Set<Integer> oldMessages = lastMessageRepository.getOldMessaged(userId);
        if(oldMessages == null){
            return;
        }
        for (Integer messageId : oldMessages){
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setMessageId(messageId);
            deleteMessage.setChatId(userId);
            try {
                bot.execute(deleteMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
        lastMessageRepository.clearRepository(userId);


    }
}