package com.example.services.withDB;

import com.example.bot.Bot;

import com.example.models.User;
import com.example.services.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;


public class UserService {
    private final SendMessageService sendMessageService;

    public UserService(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    public List<User> getDevelopersList(int groupId){
        //TODO get list of developers not from groudId (int groupID)

        List<User> developers = new ArrayList<>();
        User user = new User(1,"shrek","fiona");
        User user2 = new User(1,"peter","parker");
        developers.add(user);
        developers.add(user2);
        return developers;
    }

    public void registerUser(Update update){
        Long userId = Bot.getPlayerIdFromUpdate(update);
        String firstName = update.getMessage().getFrom().getFirstName();
        String lastName = update.getMessage().getFrom().getLastName();

        User user = new User(userId,firstName,lastName);
        //TODO create user in database (User)

        sendMessageService.sendMessage(userId,"user created");
    }
}
