package com.example.soap;

import com.example.bot.Bot;
import com.example.bot.BotInitializator;
import com.example.commands.fromSOAP.TestCommand;
import com.example.models.User;
import jakarta.jws.WebService;


@WebService(endpointInterface = "com.example.soap.RouterWebServiceSoap",
        portName = "routerPort",
        serviceName = "routerService")
public class RouterWebServiceSoapImpl implements RouterWebServiceSoap {
    static{
        BotInitializator.init();
    }
    @Override
    public User[] addUncheckedMembers(User[] members) {

        TestCommand testCommand =  (TestCommand)Bot.getContainer().receiveCommand("/test");
        testCommand.execute(members);


        return new User[0];
    }
}
