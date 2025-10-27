package dev.renzozukeram.recommendation.services;

import dev.renzozukeram.recommendation.util.UuidFilter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Service
public class GeminiChatService implements ChatService {

    private ChatClient chatClient;

    public GeminiChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public Flux<UUID> getAnswer(
            Object userInfo,
            Object userViews,
            Object userPurchases,
            Object userReturns,
            Object products) {

        String prompt =
                "The user info are: " + userInfo + ". " +
                        "The products viewed by the user are: " + userViews + ". " +
                        "The products purchased by the user are: " + userPurchases + ". " +
                        "The products returned by the user are: " + userReturns + ". " +
                        "The available products are: " + products + "." +

                        "\n\nSTRICT INSTRUCTION:\n" +
                        "1. ANALYZE THE PROVIDED DATA AND GENERATE A LIST OF RECOMMENDED PRODUCT UUIDS.\n" +
                        "2. YOUR RESPONSE MUST CONTAIN ONLY THE UUIDS, SEPARATED BY A SEMICOLON (';').\n" +
                        "3. DO NOT INCLUDE ANY CONVERSATIONAL TEXT, EXPLANATION, OR ANY OTHER CHARACTER BEFORE OR AFTER THE LIST. " +
                        "4. EXAMPLE FORMAT: 12345678-90ab-cdef-1234-567890abcdef;a1b2c3d4-e5f6-7890-1234-567890abcdef";

        Flux<String> responseFlux = chatClient.prompt()
                .user(user -> user.text(prompt))
                .stream().content();

        return responseFlux
                .collect(Collectors.joining())
                .flatMapMany(fullResponse -> {

                    List<UUID> uuids = new ArrayList<>();
                    Matcher matcher = UuidFilter.UUID_PATTERN.matcher(fullResponse);

                    while (matcher.find()) {
                        try {
                            uuids.add(UUID.fromString(matcher.group()));
                        } catch (IllegalArgumentException e) {
                            System.err.println("UUID inválido encontrado: " + matcher.group());
                        }
                    }

                    return Flux.fromIterable(uuids);
                })
                .distinct();
    }
}
