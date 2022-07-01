package com.example.commands.specificСommands;


import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import com.example.commands.base.Command;
import com.example.models.User;
import com.example.services.SendMessageService;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.ArrayList;
import java.util.List;

public class AdminCommand implements Command {
    private final Bot bot;
    private final DeleteMessageService deleteMessageService;
    private final UpdateLastReceivedMessageService updateLastReceivedMessageService;
    private final SendMessageService sendMessageService;

    public AdminCommand(DeleteMessageService deleteMessageService,Bot bot,UpdateLastReceivedMessageService updateLastReceivedMessageService,SendMessageService sendMessageService) {
        this.deleteMessageService = deleteMessageService;
        this.bot = bot;
        this.updateLastReceivedMessageService = updateLastReceivedMessageService;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        long userId = Bot.getPlayerIdFromUpdate(update);
        boolean userIsLector = Bot.getLectorId().equals(userId);
        boolean userIsTeamLead = false;
        boolean leadHasAGroup = Bot.leadHasAGroup(userId);
        for (User user : Bot.getTeamLeads()){
            if(user.getId() == userId){
                userIsTeamLead = true;
                break;
            }
        }

        if(!userIsTeamLead && !userIsLector){
            sendMessageService.sendMessage(userId,"Вы не являетесь лектором или тимлидом");
            return;
        }
        deleteMessageService.deleteMessages(userId);
        List<List<InlineKeyboardButton>> overList = new ArrayList<>();

        if(userIsLector ){
            InlineKeyboardButton createTeamLead = new InlineKeyboardButton();
            createTeamLead.setText("Добавить тимлида");
            createTeamLead.setCallbackData("/create_teamlead");

            InlineKeyboardButton deleteTeamLead = new InlineKeyboardButton();
            deleteTeamLead.setText("Удалить тимлида");
            deleteTeamLead.setCallbackData("/delete_teamlead");

            List<InlineKeyboardButton> listWithEditTeamLeadsButtons = new ArrayList<>();
            listWithEditTeamLeadsButtons.add(createTeamLead);
            listWithEditTeamLeadsButtons.add(deleteTeamLead);

            overList.add(listWithEditTeamLeadsButtons);
        }
        if(userIsTeamLead) {
            if(leadHasAGroup){
                InlineKeyboardButton addDevButton = new InlineKeyboardButton();
                addDevButton.setText("Добавить разработчика");
                addDevButton.setCallbackData("/add_dev");
                List<InlineKeyboardButton> addDevList = new ArrayList<>();
                addDevList.add(addDevButton);

                InlineKeyboardButton deleteGroupButton = new InlineKeyboardButton();
                deleteGroupButton.setText("Удалить группу");
                deleteGroupButton.setCallbackData("/delete_group");
                List<InlineKeyboardButton> deleteGroupList = new ArrayList<>();
                deleteGroupList.add(deleteGroupButton);


                overList.add(addDevList);
                overList.add(deleteGroupList);
            }else{
                InlineKeyboardButton createGroup = new InlineKeyboardButton();
                createGroup.setText("Создать группу");
                createGroup.setCallbackData("/create_group");
                List<InlineKeyboardButton> list = new ArrayList<>();
                list.add(createGroup);

                InlineKeyboardButton menu = new InlineKeyboardButton();
                menu.setText("К меню");
                menu.setCallbackData("/menu");
                List<InlineKeyboardButton> listMenu = new ArrayList<>();
                listMenu.add(menu);

                overList.add(list);
                overList.add(listMenu);
            }
        }


            InlineKeyboardMarkup markup = new InlineKeyboardMarkup(overList);
            SendMessage sm = new SendMessage();
            sm.setText("Выберите действие");
            sm.setChatId(userId);
            sm.setReplyMarkup(markup);
            try{
                Message becameMessage = bot.execute(sm);
                updateLastReceivedMessageService.update(userId, becameMessage.getMessageId());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

