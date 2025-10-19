package dev.renzozukeram.recommendation.dto;

public record PromptRequest(String userDateOfBirth, String userGender, String userViews, String userPurchases, String userReturns, String currentProducts) {
}
