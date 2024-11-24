package knu.project.crm.outflux.dto.ai;

public record RecommendAiRequest(String shoppingMallName, String product, boolean isReview, boolean isStarCount, boolean isPostDate, int k) {
}
