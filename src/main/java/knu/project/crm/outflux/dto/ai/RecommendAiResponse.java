package knu.project.crm.outflux.dto.ai;

import java.time.LocalDate;

public record RecommendAiResponse(int averageStarCount, LocalDate postDate, String productName, int review, int score) {
}
