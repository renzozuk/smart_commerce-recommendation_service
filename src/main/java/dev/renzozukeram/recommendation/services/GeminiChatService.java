package dev.renzozukeram.recommendation.services;

import dev.renzozukeram.recommendation.dto.PromptRequest;
import dev.renzozukeram.recommendation.feignClients.InventoryAccessService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiChatService implements ChatService {

    private ChatClient chatClient;
    private InventoryAccessService inventoryAccessService;

    public GeminiChatService(ChatClient chatClient, InventoryAccessService inventoryAccessService) {
        this.chatClient = chatClient;
        this.inventoryAccessService = inventoryAccessService;
    }

    @Override
    public String getAnswer(PromptRequest promptRequest) {

        String prompt = "The user date of birth is " + promptRequest.userDateOfBirth() + "." +
                "The user is " + promptRequest.userGender() + "." +
                "The products viewed by the user are: " + promptRequest.userViews() + "." +
                "The products purchased by the user are: " + promptRequest.userPurchases() + "." +
                "The products returned by the user are: " + promptRequest.userReturns() + "." +
                "The available products are: " + inventoryAccessService.getAllProductsAsString() + ".";

        return chatClient.prompt()
                .user(user -> user.text(prompt))
                .call().content();
    }
}
