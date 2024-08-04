package ru.kors.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

public class RegisterBotCommand implements TelegramBotCommand{
    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = update.message().chat().id();

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Да").callbackData("YES_REGISTER"),
                new InlineKeyboardButton("Нет").callbackData("NO_REGISTER"),
                new InlineKeyboardButton("\uD83D\uDD17url").url("www.google.com"),
                new InlineKeyboardButton("Поделиться").switchInlineQuery("text")
        );

        var request = new SendMessage(chatId,
                "Вы действительно хотите зарегистрироваться?")
                .replyMarkup(keyboard);

        bot.execute(request);
    }
}
