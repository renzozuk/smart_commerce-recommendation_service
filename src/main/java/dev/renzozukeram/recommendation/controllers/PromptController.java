package dev.renzozukeram.recommendation.controllers;

import dev.renzozukeram.recommendation.dto.PromptRequest;
import dev.renzozukeram.recommendation.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class PromptController {

    @Autowired
    private ChatService chatService;


    @GetMapping
    public ResponseEntity<String> prompt(@RequestBody PromptRequest promptRequest) {
        return ResponseEntity.ok(chatService.getAnswer(promptRequest));
    }
}
