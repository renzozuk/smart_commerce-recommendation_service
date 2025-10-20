package dev.renzozukeram.recommendation.services;

import java.util.List;

public interface ChatService {
    List<String> getAnswer(String userDateOfBirth, String userGender, String userViews, String userPurchases, String userReturns, String userProducts);
}
