package com.example.commands.base;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    void execute(Update update);
}
