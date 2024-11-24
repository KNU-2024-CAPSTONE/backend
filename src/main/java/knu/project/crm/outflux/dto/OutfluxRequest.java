package knu.project.crm.outflux.dto;

public record OutfluxRequest(int lastPurchase, int lastRefund, int refundPercent, int reIssue) {
}
