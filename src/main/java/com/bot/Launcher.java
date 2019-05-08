package com.bot;

import com.handlers.PhrasalHandler;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Launcher {
    public static final String ENVIROMENT = "PRO";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new PhrasalHandler());

        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }
}
