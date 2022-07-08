package com.example.commands.fromSOAP;

import com.example.bot.Bot;
import com.example.bot.LectorId;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;


public class SendReportsCommand {
    private final Bot bot;


    public SendReportsCommand(Bot bot) {
        this.bot = bot;
    }

    public void execute(File file) {


        try {
            SendDocument document = new SendDocument();
            document.setDocument(new InputFile(file));
            document.setChatId(LectorId.getId());
            document.setCaption("reports");

            bot.execute(document);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}

