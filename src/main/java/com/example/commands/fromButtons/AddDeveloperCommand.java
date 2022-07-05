package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import com.example.commands.base.Command;
import com.example.models.User;
import com.example.services.SendMessageService;
import com.example.services.withDB.GroupService;
import com.example.services.withDB.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class AddDeveloperCommand implements Command {
    private final Bot bot;
    private final UpdateLastReceivedMessageService updateLastReceivedMessageService;
    private final UserService userService;
    private final GroupService groupService;
    private final DeleteMessageService deleteMessageService;
    private final SendMessageService sendMessageService;

    public AddDeveloperCommand(Bot bot, UpdateLastReceivedMessageService updateLastReceivedMessageService, UserService userService, GroupService groupService, DeleteMessageService deleteMessageService, SendMessageService sendMessageService) {
        this.bot = bot;
        this.updateLastReceivedMessageService = updateLastReceivedMessageService;
        this.userService = userService;
        this.groupService = groupService;
        this.deleteMessageService = deleteMessageService;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);

        int groupId = groupService.getGroupIdByTeamLeadId(userId).getId();
        List<User> developers = userService.getDevelopersList(groupId);

        if(developers == null || developers.isEmpty() ){
               sendMessageService.sendMessage(userId,"Нет разработчиков, которых возможно добавить в группу");
               return;
        }

        deleteMessageService.deleteMessages(userId);
        List<List<InlineKeyboardButton>> overList = new ArrayList<>();
        for (User user : developers) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(user.getFirstName() + " " + user.getLastName());
            button.setCallbackData("/register_dev "+user.getTelegramId());

            List<InlineKeyboardButton> list = new ArrayList<>();
            list.add(button);
            overList.add(list);
        }

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText("Назад");
        backButton.setCallbackData("/admin");
        List<InlineKeyboardButton> list = new ArrayList<>();
        list.add(backButton);
        overList.add(list);

        SendMessage sm = new SendMessage();
        sm.setReplyMarkup(new InlineKeyboardMarkup(overList));
        sm.setChatId(userId);
        sm.setText("Нажмите на разработчика, чтобы добавить в свою группу");

        try {
            Message becameMessage =  bot.execute(sm);
            updateLastReceivedMessageService.update(userId,becameMessage.getMessageId());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
