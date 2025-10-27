package dev.renzozukeram.recommendation.services;

import java.util.List;

public interface ChatService {
    List<String> getAnswer(Object userInfo, Object userViews, Object userPurchases, Object userReturns, Object products);
}
