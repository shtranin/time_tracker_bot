package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.services.withDB.GroupService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RegisterDeveloperInGroup implements Command {
    private final GroupService groupService;
    private final DeleteMessageService deleteMessageService;


    public RegisterDeveloperInGroup(GroupService groupService, DeleteMessageService deleteMessageService) {
        this.groupService = groupService;

        this.deleteMessageService = deleteMessageService;
    }

    @Override
    public void execute(Update update) {

        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        int groupId = 1; //TODO groupid
        groupService.addUserInGroup(userId,groupId);
        Redirector.redirect("/add_dev",update);


    }
}
