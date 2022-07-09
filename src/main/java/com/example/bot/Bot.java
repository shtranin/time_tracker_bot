package com.example.bot;


import com.example.commands.base.Command;
import com.example.commands.base.CommandContainer;
import com.example.userStatementManager.Statements;
import com.example.userStatementManager.UserStatementManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


public class Bot extends TelegramLongPollingBot {
    private static final Long lectorId;
    private static CommandContainer container;


    static{
        lectorId = LectorId.getId();
        //lectorId = 1L;
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
    public static Long getLectorId(){
        return lectorId;
    }

    @Override
    public String getBotUsername() {
        return "kefx_bot";
    }

    @Override
    public String getBotToken() {
        return "5344335922:AAGn_Qi8uYbXDmqwN-gaWwjl-JwTcuzRYD8";
    }

}
