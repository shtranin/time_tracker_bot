package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.models.Group;
import com.example.services.SendMessageService;
import com.example.services.withDB.GroupService;
import com.example.userStatementManager.UserStatementManager;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RegisterGroupCommand implements Command {
    private final GroupService groupService;
    private final SendMessageService sendMessageService;
    private final DeleteMessageService deleteMessageService;

    public RegisterGroupCommand(GroupService groupService, SendMessageService sendMessageService, DeleteMessageService deleteMessageService) {
        this.groupService = groupService;
        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
    }
    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        String color = update.getMessage().getText();
        Group newGroup = new Group(color,userId);
        Bot.setLeaderAsGroupOwner(userId,true);
        groupService.sendGroup(newGroup);

        UserStatementManager.makeUserFree(userId);
        Redirector.redirect("/admin",update);
        sendMessageService.sendMessage(userId,"Группа " + color + " успешно создана");

    }
}
