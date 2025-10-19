package dev.renzozukeram.recommendation.services;

import dev.renzozukeram.recommendation.dto.PromptRequest;

public interface ChatService {
    String getAnswer(PromptRequest promptRequest);
}
