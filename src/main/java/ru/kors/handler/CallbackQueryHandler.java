package ru.kors.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class CallbackQueryHandler {
    private final TelegramBot bot;

    public CallbackQueryHandler(TelegramBot bot) {
        this.bot = bot;
    }

    public void processCallbackQuery(CallbackQuery callbackQuery) {
        String callbackData = callbackQuery.data();
        long chatId = callbackQuery.from().id();

        switch (callbackData) {
            case "YES_REGISTER" -> {
                EditMessageText message = new EditMessageText(chatId,
                        callbackQuery.message().messageId(),
                        "Идет процесс регистрации...");
                bot.execute(message);
            }
            case "NO_REGISTER" -> {
                bot.execute(new SendMessage(chatId,
                        "Процесс регистрации прерван..."));
            }

        }
    }
}
