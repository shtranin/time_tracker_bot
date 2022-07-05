package com.example.soap;

import com.example.bot.BotInitializator;
import com.example.bot.LectorId;
import com.example.soap.model.ExpiredUsers;
import jakarta.jws.WebService;


@WebService(endpointInterface = "com.example.soap.RouterWebServiceSoap",
        portName = "routerPort",
        serviceName = "routerService")
public class RouterWebServiceSoapImpl implements RouterWebServiceSoap {
    private static Long lectorId;
    static{
        BotInitializator.init();
        lectorId = LectorId.getId();
    }

    @Override
    public void sendExpiredUsersToLector(ExpiredUsers users) {
            BotInitializator.getNotificationCommand().execute(users,lectorId);
    }

    @Override
    public void sendExpiredUsersToTeamLead(ExpiredUsers users) {
            BotInitializator.getNotificationCommand().execute(users, users.getOwnerId());
    }
}
