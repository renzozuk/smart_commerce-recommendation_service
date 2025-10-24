package dev.renzozukeram.recommendation.services;

import dev.renzozukeram.recommendation.util.UuidFilter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@Service
public class GeminiChatService implements ChatService {

    private ChatClient chatClient;

    public GeminiChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public Flux<String> getAnswer(
            Object userDateOfBirth,
            Object userGender,
            Object userViews,
            Object userPurchases,
            Object userReturns,
            Object products) {

        String prompt = "The user date of birth is " + userDateOfBirth + ". " +
                "The user is " + userGender + ". " +
                "The products viewed by the user are: " + userViews + ". " +
                "The products purchased by the user are: " + userPurchases + ". " +
                "The products returned by the user are: " + userReturns + ". " +
                "The available products are: " + products + "." +
                " IMPORTANT: Respond ONLY with a list of recommended product UUIDs, separated by a semicolon (';').";

        Flux<String> responseFlux = chatClient.prompt()
                .user(user -> user.text(prompt))
                .stream().content();

        return responseFlux
                .flatMapIterable(chunk -> {

                    List<String> uuids = new ArrayList<>();
                    Matcher matcher = UuidFilter.UUID_PATTERN.matcher(chunk);

                    while (matcher.find()) {
                        uuids.add(matcher.group());
                    }

                    return uuids;
                })
                .distinct();
    }
}
