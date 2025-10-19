package dev.renzozukeram.recommendation.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {

        var systemPrompt = "You'll receive the following information about a specific customer: date of birth, gender, viewed products (json), purchased products (json), and returned products (json). You'll also receive the available products (json). Your job is to recommend products based on this information.";

        return chatClientBuilder.defaultSystem(systemPrompt).build();
    }
}
