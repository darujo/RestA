package ru.darujo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeAllGroupChats;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeAllPrivateChats;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.daru_jo.model.ChatInfo;
import ru.daru_jo.telegram_bot.MessageService;
import ru.daru_jo.telegram_bot.TelegramBotSend;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {
    private TelegramBotSend telegramBotSend;

    @Autowired
    public void setTelegramBotSend(TelegramBotSend telegramBotSend) {
        this.telegramBotSend = telegramBotSend;
    }

    @Override
    public void command(ChatInfo chatInfo, String command) throws TelegramApiException {
        telegramBotSend.sendMessage(chatInfo, "не могу выполнить " + command);
    }

    @Override
    public void message(ChatInfo chatInfo, String text) throws TelegramApiException {
        telegramBotSend.sendMessage(chatInfo, "записал: " + text);
    }

    @Override
    public void getMenu(ChatInfo chatInfo, String command) {

    }

    @PostConstruct
    public void init() {
        List<BotCommand> botCommands = new ArrayList<>();
        botCommands.add(new BotCommand("/menu", "Открыть меню"));
        botCommands.add(new BotCommand("/stop", "Отвязать аккаунт от уведомлений"));
        botCommands.add(new BotCommand("/link", "Подписаться на уведомления от сервиса трудо затрат"));
        SetMyCommands setMyCommands = new SetMyCommands(botCommands);
        setMyCommands.setScope(new BotCommandScopeAllPrivateChats());
        try {
            telegramBotSend.setCommand(setMyCommands);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        setMyCommands.setScope(new BotCommandScopeAllGroupChats());
        try {
            telegramBotSend.setCommand(setMyCommands);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
