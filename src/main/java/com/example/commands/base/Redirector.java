package com.example.commands.base;

import com.example.bot.Bot;
import com.example.clearing_old_messages.DeletingBlocker;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Redirector {
    private static final CommandContainer container = Bot.getContainer();

    public static void redirect(String command, Update update){
        DeletingBlocker.setBlock(Bot.getPlayerIdFromUpdate(update));
        container.receiveCommand(command).execute(update);

    }
}
