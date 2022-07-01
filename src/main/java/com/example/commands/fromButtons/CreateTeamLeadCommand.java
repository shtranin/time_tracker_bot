package com.example.commands.fromButtons;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeleteMessageService;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import com.example.commands.base.Command;
import com.example.commands.base.Redirector;
import com.example.models.Group;
import com.example.models.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamLeadCommand implements Command {
   private final Bot bot;
   private UpdateLastReceivedMessageService updateLastReceivedMessageService;
   private final DeleteMessageService deleteMessageService;

    public CreateTeamLeadCommand(Bot bot,UpdateLastReceivedMessageService updateLastReceivedMessageService,DeleteMessageService deleteMessageService) {
        this.bot = bot;
        this.updateLastReceivedMessageService = updateLastReceivedMessageService;
        this.deleteMessageService = deleteMessageService;
    }

    @Override
    public void execute(Update update) {
        Long userId = Bot.getPlayerIdFromUpdate(update);
        deleteMessageService.deleteMessages(userId);
        //TODO asking list of users
        List<User> users = new ArrayList<>();
        users.add(new User(228L,"kek","cheburek"));

        List<List<InlineKeyboardButton>> overList = new ArrayList<>();
        for (User user : users) {
            InlineKeyboardButton userButton = new InlineKeyboardButton();
            userButton.setText(user.getFirstName() + " " + user.getLastName());
            userButton.setCallbackData("/register_teamlead " + user.getId() + " " + user.getFirstName() + " " + user.getLastName());

            List<InlineKeyboardButton> list = new ArrayList<>();
            list.add(userButton);
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
            sm.setText("Нажмите на пользователя, чтобы назначить его тимлидом");
            try {
               Message becameMessage =  bot.execute(sm);
               updateLastReceivedMessageService.update(userId,becameMessage.getMessageId());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
}


