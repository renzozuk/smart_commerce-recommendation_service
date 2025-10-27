package dev.renzozukeram.recommendation.services;

import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ChatService {
    Flux<UUID> getAnswer(Object userInfo, Object userViews, Object userPurchases, Object userReturns, Object products);
}
