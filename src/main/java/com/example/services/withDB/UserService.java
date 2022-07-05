package com.example.services.withDB;

import com.example.bot.Bot;
import com.example.models.User;
import com.example.services.SendMessageService;
import com.example.soap.client.SoapClientForTeamService;
import com.example.teamLeadsManager.TeamLeadsManager;
import com.example.teamLeadsManager.model.TeamLead;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class UserService {
    private final SendMessageService sendMessageService;
    private final TeamLeadsManager teamLeadsManager;

    public UserService(SendMessageService sendMessageService, TeamLeadsManager teamLeadsManager) {
        this.sendMessageService = sendMessageService;
        this.teamLeadsManager = teamLeadsManager;
    }

    public List<User> getDevelopersList(int groupId){
        List<User> developers = new SoapClientForTeamService().getDevelopersList(groupId);
        return developers;
    }

    public void registerUser(Update update){
        Long userId = Bot.getPlayerIdFromUpdate(update);
        String firstName = update.getMessage().getFrom().getFirstName();
        String lastName = update.getMessage().getFrom().getLastName();

        User user = new User(userId,firstName,lastName);
        new SoapClientForTeamService().createUser(user);

    }
    public List<User> getUsersNotTeamleads(){
        List<User> users = new SoapClientForTeamService().getUsersNotLeamleads();
        Set<Long> teamLeads = teamLeadsManager.findAll().stream().map(TeamLead::getTelegramId).collect(Collectors.toSet());
           return users.stream().filter(x -> !teamLeads.contains(x.getTelegramId())).collect(Collectors.toList());

    }
}
