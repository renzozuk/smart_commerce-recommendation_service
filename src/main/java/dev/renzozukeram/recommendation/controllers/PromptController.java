package dev.renzozukeram.recommendation.controllers;

import dev.renzozukeram.recommendation.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class PromptController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<List<String>> prompt(String userDateOfBirth, String userGender, String userViews, String userPurchases, String userReturns, String userProducts) {
        return ResponseEntity.ok(chatService.getAnswer(userDateOfBirth, userGender, userViews, userPurchases, userReturns, userProducts));
    }
}
