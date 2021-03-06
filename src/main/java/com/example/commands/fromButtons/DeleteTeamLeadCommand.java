package com.example.commands.fromButtons;


import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.services.SendMessageService;
import com.example.teamLeadsManager.TeamLeadsManager;
import com.example.teamLeadsManager.model.TeamLead;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class DeleteTeamLeadCommand implements Command {
    private final Bot bot;
    private final SendMessageService sendMessageService;
    private final DeleteMessageService deleteMessageService;
    private final UpdateLastReceivedMessageService updateLastReceivedMessageService;
    private final TeamLeadsManager teamLeadsManager;

    public DeleteTeamLeadCommand(Bot bot, SendMessageService sendMessageService, DeleteMessageService deleteMessageService, UpdateLastReceivedMessageService updateLastReceivedMessageService, TeamLeadsManager teamLeadsManager) {
        this.bot = bot;
        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
        this.updateLastReceivedMessageService = updateLastReceivedMessageService;
        this.teamLeadsManager = teamLeadsManager;
    }



    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        //List<User> users = Bot.getTeamLeads();
        List<TeamLead> users = teamLeadsManager.findAll();
        if(users.isEmpty()){
            Redirector.redirect("/admin",update);
            sendMessageService.sendMessage(userId,"???????????????? ???????? ?????? :)");
            return;
        }


        List<List<InlineKeyboardButton>> overList = new ArrayList<>();
        for (TeamLead user : users) {
            InlineKeyboardButton userButton = new InlineKeyboardButton();
            userButton.setText(user.getFirstName() + " " + user.getLastName());
            userButton.setCallbackData("/remove_teamlead " + user.getTelegramId());

            List<InlineKeyboardButton> list = new ArrayList<>();
            list.add(userButton);
            overList.add(list);
        }

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText("??????????");
        backButton.setCallbackData("/admin");
        List<InlineKeyboardButton> list = new ArrayList<>();
        list.add(backButton);
        overList.add(list);


        SendMessage sm = new SendMessage();
            sm.setReplyMarkup(new InlineKeyboardMarkup(overList));
            sm.setChatId(userId);
            sm.setText("?????????????? ???? ??????????????, ?????????? ??????????????????????");
            try {
              Message becameMessage =   bot.execute(sm);
              updateLastReceivedMessageService.update(userId,becameMessage.getMessageId());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
}


