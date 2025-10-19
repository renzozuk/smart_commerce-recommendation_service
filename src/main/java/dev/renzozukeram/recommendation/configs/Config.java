package dev.renzozukeram.recommendation.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {

        var systemPrompt = "You'll receive the following information about a specific customer: date of birth, gender, viewed products, purchased products, and returned products. You'll also receive the available products. Your job is to recommend products based on this information. You must return the product ids in the following format 'id 1;id 2;id 3;...;id n-3; id n-2; id n-1'. The items should be sorted by how recommendable each one is, the most recommendable must be the first.";

        return chatClientBuilder.defaultSystem(systemPrompt).build();
    }
}
