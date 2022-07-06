package com.example.commands.fromSOAP;

import com.example.bot.Bot;
import com.example.bot.LectorId;
import com.example.soap.model.ByteArray;
import com.google.common.io.Files;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class SendReportsCommand {
    private final Bot bot;


    public SendReportsCommand(Bot bot) {
        this.bot = bot;
    }

    public void execute(ByteArray bytesArray)  {

//        File tempReportFile;
//        try {
//            tempReportFile = File.createTempFile(bytesArray.getFileName(),".pdf");
//            Files.write(bytesArray.getBytes(),tempReportFile);
//            SendDocument document = new SendDocument();
//            document.setDocument(new InputFile(tempReportFile));
//            document.setChatId(LectorId.getId());
//            document.setCaption("privet, konstantin");
//
//            bot.execute(document);
//        } catch (TelegramApiException | IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(bytesArray);


    }
}
