package ru.kors.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public interface TelegramBotCommand {
    void execute(TelegramBot bot, Update update);
}
