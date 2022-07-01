package com.example.clearing_old_messages;

public class UpdateLastReceivedMessageService {
    private LastMessageRepository lastMessageRepository;





    public UpdateLastReceivedMessageService(LastMessageRepository lastMessageRepository) {
    this.lastMessageRepository = lastMessageRepository;
    }

    public void update(Long userId, int messageId) {
    lastMessageRepository.addMessageId(userId,messageId);
    }
}
