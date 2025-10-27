package dev.renzozukeram.recommendation.controllers;

import dev.renzozukeram.recommendation.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class PromptController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public Flux<UUID> prompt(@RequestBody Map<String, Object> requestBody) {
        return chatService.getAnswer(requestBody.get("userInfo"), requestBody.get("userViews"), requestBody.get("userPurchases"), requestBody.get("userReturns"), requestBody.get("products"));
    }
}
