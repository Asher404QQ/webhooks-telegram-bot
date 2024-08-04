package ru.kors.command;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TelegramBotCommandFactory {
    private final Map<String, TelegramBotCommand> commandMap = new HashMap<>();

    public TelegramBotCommandFactory() {
        commandMap.put("/start", new StartBotCommand());
        commandMap.put("/register", new RegisterBotCommand());
    }

    public TelegramBotCommand getCommand(String command) {
        return commandMap.get(command);
    }
}
