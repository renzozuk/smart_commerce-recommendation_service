package dev.renzozukeram.recommendation.services;

import dev.renzozukeram.recommendation.dto.PromptRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeminiChatService implements ChatService {

    @Autowired
    private ChatClient chatClient;

    @Override
    public String getAnswer(PromptRequest promptRequest) {

        String prompt = "The user date of birth is " + promptRequest.userDateOfBirth() + "." +
                "The user is " + promptRequest.userGender() + "." +
                "The products viewed by the user are: " + promptRequest.userViews() + "." +
                "The products purchased by the user are: " + promptRequest.userPurchases() + "." +
                "The products returned by the user are: " + promptRequest.userReturns() + "." +
                "The available products are: " + promptRequest.currentProducts() + ".";

        return chatClient.prompt()
                .user(user -> user.text(prompt))
                .call().content();
    }
}
