package com.handlers;

import com.constants.Tokens;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BasicHandler extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {}

    public String getBotUsername() {
        return Tokens.getBotName();
    }

    public String getBotToken() {
        return Tokens.getToken();
    }
}
