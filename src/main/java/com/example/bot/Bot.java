package com.example.bot;


import com.example.commands.base.Command;
import com.example.commands.base.CommandContainer;
import com.example.models.User;
import com.example.userStatementManager.Statements;
import com.example.userStatementManager.UserStatementManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private static final List<User> teamLeads;
    private static final Long lectorId;
    private static CommandContainer container;
    private static final Map<Long,Boolean> groupAvailability;

    static{
       // lectorId = 325533383L;
        lectorId = 1L;
        groupAvailability = new HashMap<>();
        teamLeads = new ArrayList<>();
        //kefx
        teamLeads.add(new User(325533383L,"kef","x"));

    }
    public static Long getLectorId(){
        return lectorId;
    }
    public static List<User> getTeamLeads(){
        return teamLeads;
    }
    @Override
    public String getBotUsername() {
        return "kefx_bot";
    }

    @Override
    public String getBotToken() {
        return "2058968833:AAFl2L6YDuxxJfEyjQ72USVsmSGEONKXUvE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long userId = getPlayerIdFromUpdate(update);
        Command currentCommand;

        if(UserStatementManager.userHasStatement(userId)){
            Statements statement = UserStatementManager.getStatementById(userId);
            currentCommand = container.receiveCommand(statement.getName());
            currentCommand.execute(update);
            return;
        }


        if (update.hasMessage()) {
            currentCommand = container.receiveCommand(update.getMessage().getText());
        } else {
            currentCommand = container.receiveCommand(update.getCallbackQuery().getData().split(" ")[0]);
        }
        currentCommand.execute(update);
    }




    public static Long getPlayerIdFromUpdate(Update update){
        long playerId;
        if(update.hasMessage()) {
            playerId = Long.parseLong(update.getMessage().getFrom().getId().toString());
        }else{
            playerId = Long.parseLong(update.getCallbackQuery().getFrom().getId().toString());
        }
        return playerId;
    }
    public static void initContainer(CommandContainer container){
        Bot.container = container;
    }
    public static CommandContainer getContainer(){
        return container;
    }
    public static boolean leadHasAGroup(Long userId){
        return groupAvailability.getOrDefault(userId,false);
    }
    public static void setLeaderAsGroupOwner(Long userId,Boolean bool){
        groupAvailability.put(userId,bool);
    }

}
