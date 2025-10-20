package dev.renzozukeram.recommendation.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GeminiChatService implements ChatService {

    private ChatClient chatClient;

    public GeminiChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public List<String> getAnswer(String userDateOfBirth, String userGender, String userViews, String userPurchases, String userReturns, String userProducts) {

        String prompt = "The user date of birth is " + userDateOfBirth + "." +
                "The user is " + userGender + "." +
                "The products viewed by the user are: " + userViews + "." +
                "The products purchased by the user are: " + userPurchases + "." +
                "The products returned by the user are: " + userReturns + "." +
                "The available products are: " + userProducts + ".";

        String response = chatClient.prompt()
                .user(user -> user.text(prompt))
                .call().content();

        return Arrays.stream(response.split(";")).toList();
    }
}
