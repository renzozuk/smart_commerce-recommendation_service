package dev.renzozukeram.recommendation.controllers;

import dev.renzozukeram.recommendation.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class PromptController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public Flux<String> prompt(@RequestBody Map<String, Object> requestBody) {
        return chatService.getAnswer(requestBody.get("userDateOfBirth"), requestBody.get("userGender"), requestBody.get("userViews"), requestBody.get("userPurchases"), requestBody.get("userReturns"), requestBody.get("products"));
    }
}
