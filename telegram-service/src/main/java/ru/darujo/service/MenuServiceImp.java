package ru.darujo.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.daru_jo.model.ChatInfo;
import ru.daru_jo.telegram_bot.MenuService;

import java.io.File;

@Service
public class MenuServiceImp implements MenuService {
    @Override
    public void openMainMenu(ChatInfo chatInfo) throws TelegramApiException {

    }

    @Override
    public void getMenu(ChatInfo chatInfo, String command, File picture) throws TelegramApiException {

    }

    @Override
    public void openCancel(ChatInfo chatInfo, String text) {

    }
}
