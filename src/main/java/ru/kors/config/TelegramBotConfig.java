package ru.kors.config;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.DeleteWebhook;
import com.pengrad.telegrambot.request.SetWebhook;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramBotConfig {

    @Value("${telegram.webhook.url}")
    private String webhookUrl;
    @Value("${telegram.bot.token}")
    private String botToken;

    @Bean
    public TelegramBot telegramBot() {
        var bot = new TelegramBot(botToken);

        DeleteWebhook deleteWebhook = new DeleteWebhook();
        bot.execute(deleteWebhook);

        SetWebhook setWebhook = new SetWebhook()
                .url(webhookUrl);

        bot.execute(setWebhook);
        return bot;
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
