package com.example.clearing_old_messages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LastMessageRepository {

    private Map<Long, Set<Integer>> lastUsersMessages;
    {
        lastUsersMessages = new HashMap<>();
    }
    public void addMessageId(Long userId,Integer messageId){
        if(!lastUsersMessages.containsKey(userId)){
            lastUsersMessages.put(userId,new HashSet<>());
        }
        lastUsersMessages.get(userId).add(messageId);
    }
    public Set<Integer> getOldMessaged(Long userId){
        return lastUsersMessages.get(userId);
    }
    public void clearRepository(Long userId){
        lastUsersMessages.get(userId).clear();
    }


}
