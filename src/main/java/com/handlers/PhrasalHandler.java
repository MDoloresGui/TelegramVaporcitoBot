package com.handlers;

import com.constants.FuzzyLogicWords;
import com.utilities.VaporcitoUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PhrasalHandler extends BasicHandler {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    public void onUpdateReceived(Update update) {

        SendMessage sender = null;
        SendPhoto phSender = null;
        String response = "";

        if (update.hasMessage() && update.getMessage().hasText()) {
            String msg = update.getMessage().getText();
            if (VaporcitoUtils.isMatchNoCaseSensitive(FuzzyLogicWords.CAI, msg, FuzzyLogicWords.CAI_TOLERANCE)
                || VaporcitoUtils.isMatchNoCaseSensitive(FuzzyLogicWords.CADIZ, msg, FuzzyLogicWords.CADIZ_TOLERANCE)) {
                sender = new SendMessage();
                response = "OLE OLE OLE Y OLE MI CAI";
            }

            if (VaporcitoUtils.isMatchNoCaseSensitive(FuzzyLogicWords.COGEDLO_AHI, msg, FuzzyLogicWords.COGEDLO_AHI_TOLERANCE)) {
                sender = new SendMessage();
                response = "¡AAAAAAAAAAAAAY!";
            }

            if ( VaporcitoUtils.isMatchNoCaseSensitive(FuzzyLogicWords.VAPORCITO, msg, FuzzyLogicWords.VAPORCITO_TOLERANCE)
                    || VaporcitoUtils.isMatchNoCaseSensitive(FuzzyLogicWords.ADRIANO, msg, FuzzyLogicWords.ADRIANO_TOLERANCE)) {
                phSender = new SendPhoto();
                phSender.setPhoto("VaporcitoDying.jpeg", classLoader.getResourceAsStream("VaporcitoDying.jpeg"));
                phSender.setCaption("PRESS F TO PAY RESPECTS");
            }
        }

        if (sender != null) {
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
}
