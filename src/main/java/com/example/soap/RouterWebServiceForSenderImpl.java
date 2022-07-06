package com.example.soap;

import com.example.bot.BotInitializator;
import com.example.soap.model.ByteArray;
import jakarta.jws.WebService;

@WebService(endpointInterface = "com.example.soap.RouterWebServiceForSender",
        portName = "routerPort",
        serviceName = "routerService")
public class RouterWebServiceForSenderImpl implements RouterWebServiceForSender {
    @Override
    public void sendReports(ByteArray bytes) {

        BotInitializator.getSendReportsCommand().execute(bytes);
    }
}
