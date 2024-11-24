package knu.project.crm.product_recommend.dto;

public record ProductRecommendRequest(boolean isStarCount, boolean isReview, boolean isPostDate, int k, int conversionRate) {
}
