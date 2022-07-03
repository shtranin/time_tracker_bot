package com.example.bot;

import com.example.clearing_old_messages.DeleteMessageService;
import com.example.clearing_old_messages.LastMessageRepository;
import com.example.clearing_old_messages.UpdateLastReceivedMessageService;
import com.example.commands.base.CommandContainer;
import com.example.commands.fromButtons.*;
import com.example.commands.fromButtons.RegisterGroupCommand;
import com.example.commands.fromButtons.RemoveGroupCommand;
import com.example.commands.fromButtons.RemoveTeamLeadCommand;
import com.example.commands.fromButtons.RemoveTrackCommand;
import com.example.commands.fromSOAP.NotificationToLectorCommand;
import com.example.commands.fromSOAP.TestCommand;
import com.example.commands.phaseCommand.TakeDescriptionOfTrack;
import com.example.commands.phaseCommand.TakingSpentTimeForSolution;
import com.example.commands.specificСommands.AdminCommand;
import com.example.commands.specificСommands.MenuCommand;
import com.example.commands.specificСommands.StartCommand;
import com.example.commands.specificСommands.UnknownCommand;
import com.example.services.withDB.UserService;
import com.example.services.SendMessageService;
import com.example.services.withDB.GroupService;
import com.example.services.withDB.TracksService;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class BotInitializator {
    private static NotificationToLectorCommand notificationToLectorCommand;
    public static NotificationToLectorCommand getNotificationToLectorCommand(){
        return notificationToLectorCommand;
    }
    public static void init() {
        Bot bot = new Bot();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            System.out.println("exception registracii");
        }

        LastMessageRepository lastMessageRepository = new LastMessageRepository();
        DeleteMessageService deleteMessageService = new DeleteMessageService(bot, lastMessageRepository);
        UpdateLastReceivedMessageService updateLastReceivedMessageService = new UpdateLastReceivedMessageService(lastMessageRepository);
        SendMessageService sendMessageService = new SendMessageService(bot, updateLastReceivedMessageService);
        UserService userService = new UserService(sendMessageService);
        UnknownCommand unknownCommand = new UnknownCommand(sendMessageService, bot);
        StartCommand startCommand = new StartCommand(userService, sendMessageService);
        TestCommand testCommand = new TestCommand(sendMessageService);
        TracksService tracksService = new TracksService();
        GroupService groupService = new GroupService();
        notificationToLectorCommand = new NotificationToLectorCommand(sendMessageService);
        RegisterDeveloperInGroup registerDeveloperInGroup = new RegisterDeveloperInGroup(groupService, deleteMessageService);
        AddDeveloperCommand addDeveloperCommand = new AddDeveloperCommand(bot, updateLastReceivedMessageService, userService, deleteMessageService);
        GetTodayTracksCommand getTodayTracksCommand = new GetTodayTracksCommand(tracksService, bot, deleteMessageService, updateLastReceivedMessageService);
        RegisterTeamLeadCommand registerTeamLeadCommand = new RegisterTeamLeadCommand(sendMessageService, deleteMessageService);
        RemoveTeamLeadCommand removeTeamLeadCommand = new RemoveTeamLeadCommand(groupService, sendMessageService, deleteMessageService);
        RemoveGroupCommand removeGroupCommand = new RemoveGroupCommand(groupService, sendMessageService, deleteMessageService);
        RemoveTrackCommand removeTrackCommand = new RemoveTrackCommand(sendMessageService, deleteMessageService, tracksService);
        TakeDescriptionOfTrack takeDescriptionOfTrack = new TakeDescriptionOfTrack(sendMessageService, deleteMessageService);
        TakingSpentTimeForSolution takingSpentTimeForSolution = new TakingSpentTimeForSolution(tracksService, sendMessageService, deleteMessageService);
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(sendMessageService, deleteMessageService);
        CreateTrackCommand createTrackCommand = new CreateTrackCommand(sendMessageService, deleteMessageService);
        RegisterGroupCommand registerGroupCommand = new RegisterGroupCommand(groupService, sendMessageService, deleteMessageService);
        DeleteTeamLeadCommand deleteTeamLeadCommand = new DeleteTeamLeadCommand(bot, sendMessageService, deleteMessageService, updateLastReceivedMessageService);
        CreateTeamLeadCommand createTeamLeadCommand = new CreateTeamLeadCommand(bot, updateLastReceivedMessageService, deleteMessageService);
        AdminCommand adminCommand = new AdminCommand(deleteMessageService, bot, updateLastReceivedMessageService, sendMessageService);
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(bot, deleteMessageService, updateLastReceivedMessageService);
        MenuCommand menuCommand = new MenuCommand(bot, updateLastReceivedMessageService, deleteMessageService);
        CommandContainer container = new CommandContainer(unknownCommand, startCommand, menuCommand, adminCommand, deleteGroupCommand, testCommand, createTeamLeadCommand, registerTeamLeadCommand, removeTeamLeadCommand, removeGroupCommand, createGroupCommand, deleteTeamLeadCommand,
                registerGroupCommand, createTrackCommand, takeDescriptionOfTrack, takingSpentTimeForSolution, getTodayTracksCommand, removeTrackCommand, addDeveloperCommand, registerDeveloperInGroup);
        Bot.initContainer(container);

    }
}


