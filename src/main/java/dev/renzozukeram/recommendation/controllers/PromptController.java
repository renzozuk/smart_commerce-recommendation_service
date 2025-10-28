package dev.renzozukeram.recommendation.controllers;

import dev.renzozukeram.recommendation.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PromptController {

    @Autowired
    private ChatService chatService;

    public record PromptInput(
            String userInfo,
            String userViews,
            String userPurchases,
            String userReturns,
            String products
    ) {}

    @MutationMapping
    public List<String> prompt(@Argument PromptInput input) {
        return chatService.getAnswer(
                input.userInfo(),
                input.userViews(),
                input.userPurchases(),
                input.userReturns(),
                input.products()
        );
    }
}
