package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.services.SendMessageService;
import com.example.services.withDB.GroupService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RemoveGroupCommand implements Command {
    private final GroupService groupService;
    private final SendMessageService sendMessageService;
    private final DeleteMessageService deleteMessageService;

    public RemoveGroupCommand(GroupService groupService, SendMessageService sendMessageService, DeleteMessageService deleteMessageService) {
        this.groupService = groupService;
        this.sendMessageService = sendMessageService;
        this.deleteMessageService = deleteMessageService;
    }
    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        Long teamLeadId = Long.parseLong(update.getCallbackQuery().getData().split(" ")[1]);
        Bot.setLeaderAsGroupOwner(userId,false);
        groupService.removeGroupByTeamLeadId(teamLeadId);
        Redirector.redirect("/admin",update);
        sendMessageService.sendMessage(userId,"Группа удалена");
    }
}
