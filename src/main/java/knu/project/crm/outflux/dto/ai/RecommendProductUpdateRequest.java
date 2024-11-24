package knu.project.crm.outflux.dto.ai;

import knu.project.crm.outflux.dto.ProductListResponse;

import java.util.List;

public record RecommendProductUpdateRequest(List<ProductListResponse> productList, String shoppingMallName, String product, int k) {
}
