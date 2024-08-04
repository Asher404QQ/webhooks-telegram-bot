package ru.kors.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kors.service.TelegramBotService;

@RestController
public class TelegramBotController {
    private final TelegramBotService telegramBotService;

    public TelegramBotController(TelegramBotService telegramBotService) {
        this.telegramBotService = telegramBotService;
    }


    @PostMapping
    public ResponseEntity<?> getUpdate(@RequestBody String stringRequest) {
        telegramBotService.execute(stringRequest);
        return ResponseEntity.ok(stringRequest);
    }
}
