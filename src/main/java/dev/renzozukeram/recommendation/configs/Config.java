package dev.renzozukeram.recommendation.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {

        var systemPrompt = "You'll receive the following information about a specific customer: date of birth, gender, viewed products, purchased products, and returned products. You'll also receive the available products. Your job is to recommend products based on this information.";

        return chatClientBuilder.defaultSystem(systemPrompt).build();
    }
}
