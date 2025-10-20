package dev.renzozukeram.recommendation.services;

import java.util.List;

public interface ChatService {
    List<String> getAnswer(Object userDateOfBirth, Object userGender, Object userViews, Object userPurchases, Object userReturns, Object products);
}
