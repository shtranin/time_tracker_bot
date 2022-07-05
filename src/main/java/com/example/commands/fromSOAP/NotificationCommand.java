package com.example.commands.fromSOAP;


import com.example.bot.LectorId;
import com.example.models.User;
import com.example.services.SendMessageService;
import com.example.soap.model.ExpiredUsers;

import java.text.SimpleDateFormat;
import java.util.List;

public class NotificationCommand {

    private final SendMessageService sendMessageService;

    public NotificationCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    public void execute(ExpiredUsers users,Long receiverId){

        String introduceLine;
        if(receiverId.equals(LectorId.getId())){
            introduceLine = "Разработчики, которые не трекали время 3 или более дней:\n";
        }else{
            introduceLine = "Разработчики, которые сегодня не трекали время:\n";
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM");
        StringBuilder builder = new StringBuilder();
        builder.append(introduceLine);


       List<User> expiredUsers =  users.getExpiredUsers();
       for (User user : expiredUsers){
           builder.append(user.getFirstName() + " " + user.getLastName() + "последний трек - " + formatter.format(user.getLastmodified())+ "\n");
       }
       sendMessageService.sendMessage(receiverId,builder.toString());


    }
}
 