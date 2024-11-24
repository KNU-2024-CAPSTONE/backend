package knu.project.crm.outflux.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record OutfluxResponse(int lastPurchase, int lastRefund, int refundPercent, int reIssue, int purchaseWithCategory, int purchaseNumber) {
}
