package com.handlers;

import com.constants.Tokens;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class PhrasalHandler extends TelegramLongPollingBot {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    public void onUpdateReceived(Update update) {
        String msg = update.getMessage().getText();
        SendMessage sender = new SendMessage();
        SendPhoto phSender = null;
        String response = "";

        if (update.hasMessage() && update.getMessage().hasText()) {
            if (msg.toLowerCase().contains("cogedlo ahi") || msg.toLowerCase().contains("cogedlo ahí")
                    || msg.toLowerCase().contains("cogerlo ahi") || msg.toLowerCase().contains("cogerlo ahí")) {
                response = "¡AAAAAAY!";
            }

            if (msg.toLowerCase().contains("cai") || msg.toLowerCase().contains("cadiz") || msg.toLowerCase().contains("cádiz")) {
                response = "OLE OLE Y OLE MI CAI";
            }

            if (msg.toLowerCase().contains("vaporcito") || msg.toLowerCase().contains("adriano")) {
                phSender = new SendPhoto();
                phSender.setPhoto("VaporcitoDying.jpeg", classLoader.getResourceAsStream("VaporcitoDying.jpeg"));
                phSender.setCaption("PRESS F TO PAY RESPECTS");
            }
        }

        if (!response.isEmpty()) {
            sender.setText(response);
            sender.setReplyToMessageId(update.getMessage().getMessageId());
            sender.setChatId(update.getMessage().getChatId());
            try {
                execute(sender);
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        }

        if (phSender != null) {
            phSender.setChatId(update.getMessage().getChatId());
            phSender.setReplyToMessageId(update.getMessage().getMessageId());
            try {
                execute(phSender);
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        }
    }


    public String getBotUsername() {
        return Tokens.DES_BOT_NAME;
    }

    public String getBotToken() {
        return Tokens.DES_TOKEN;
    }
}
