package dev.renzozukeram.recommendation.services;

import reactor.core.publisher.Flux;

public interface ChatService {
    Flux<String> getAnswer(Object userDateOfBirth, Object userGender, Object userViews, Object userPurchases, Object userReturns, Object products);
}
