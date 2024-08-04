package ru.kors.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.utility.BotUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import ru.kors.command.TelegramBotCommand;
import ru.kors.command.TelegramBotCommandFactory;
import ru.kors.handler.CallbackQueryHandler;

@Service
public class TelegramBotService {
    private final TelegramBot telegramBot;
    private final TelegramBotCommandFactory commandFactory;
    private final CallbackQueryHandler callbackQueryHandler;
    private final ChatClient chatClient;

    public TelegramBotService(TelegramBot telegramBot, TelegramBotCommandFactory commandFactory, CallbackQueryHandler callbackQueryHandler, ChatClient chatClient) {
        this.telegramBot = telegramBot;
        this.commandFactory = commandFactory;
        this.callbackQueryHandler = callbackQueryHandler;
        this.chatClient = chatClient;
    }

    @PostConstruct
    public void init() {
        SetMyCommands setMyCommands = new SetMyCommands(
                new BotCommand("/start", "Получить приветственное сообщение"),
                new BotCommand("/register", "Зарегистрировать пользователя")
        );

        telegramBot.execute(setMyCommands);
    }

    public void execute(String stringRequest) {
        Update update = BotUtils.parseUpdate(stringRequest);
        if (update.message() != null && update.message().text() != null) {
            String userMessage = update.message().text();
            TelegramBotCommand command = commandFactory.getCommand(userMessage);

            if (command != null) {
                command.execute(telegramBot, update);
            } else {
                long chatId = update.message().chat().id();

                SendMessage request = new SendMessage(chatId, chatClient.prompt()
                        .user(userMessage)
                        .call()
                        .content());
                telegramBot.execute(request);
            }
        } else if (update.callbackQuery() != null) {
            callbackQueryHandler.processCallbackQuery(update.callbackQuery());
        }
    }
}
