package ru.kors.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

public class StartBotCommand implements TelegramBotCommand{
    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = update.message().chat().id();

        String responseMessage = """
                Привет! Я - умный помощник.

                Могу 😎решить для тебя практически любую задачу в программировании или сгенерировать код по твоему запросу.
                Могу распознавать изображения или генерировать новые. Еще я могу производить поиск в GitHub и StackOverFlow!
                """;

        Keyboard keyboard = new ReplyKeyboardMarkup(
                new String[]{"first row button 1", "first row button 2"},
                new String[]{"second row button 1"},
                new String[]{"third row button 1", "third row button 2"}
        );

        SendMessage sendMessage = new SendMessage(chatId, responseMessage)
                .parseMode(ParseMode.Markdown)
                .replyMarkup(keyboard);

        bot.execute(sendMessage);
    }
}
