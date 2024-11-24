package knu.project.crm.outflux.dto.ai;

public record AddCouponRequest(Long memberId, String category, String code, int discount) {
}
