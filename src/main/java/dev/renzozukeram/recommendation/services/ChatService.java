package dev.renzozukeram.recommendation.services;

import dev.renzozukeram.recommendation.dto.PromptRequest;

import java.util.List;

public interface ChatService {
    List<String> getAnswer(PromptRequest promptRequest);
}
