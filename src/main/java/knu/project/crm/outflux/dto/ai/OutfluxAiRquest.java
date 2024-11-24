package knu.project.crm.outflux.dto.ai;

import knu.project.crm.outflux.dto.PurchaseLogRequest;

import java.util.List;

public class OutfluxAiRquest {
    private List<PurchaseLogRequest> purchaseLog;
    private int lastPurchase;
    private int lastRefund;
    private int refundPercent;
    private int purchaseWithCategory;
    private int purchaseNumber;

    public OutfluxAiRquest(){}

    public OutfluxAiRquest(List<PurchaseLogRequest> purchaseLog, int lastPurchase, int lastRefund, int refundPercent, int purchaseWithCategory, int purchaseNumber){
        this.purchaseLog = purchaseLog;
        this.lastPurchase = lastPurchase;
        this.lastRefund = lastRefund;
        this.refundPercent = refundPercent;
        this.purchaseWithCategory = purchaseWithCategory;
        this.purchaseNumber = purchaseNumber;
    }

    public List<PurchaseLogRequest> getPurchaseLog() {
        return purchaseLog;
    }

    public void setPurchaseLog(List<PurchaseLogRequest> purchaseLog) {
        this.purchaseLog = purchaseLog;
    }

    public int getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(int lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public int getLastRefund() {
        return lastRefund;
    }

    public void setLastRefund(int lastRefund) {
        this.lastRefund = lastRefund;
    }

    public int getRefundPercent() {
        return refundPercent;
    }

    public void setRefundPercent(int refundPercent) {
        this.refundPercent = refundPercent;
    }

    public int getPurchaseWithCategory() {
        return purchaseWithCategory;
    }

    public void setPurchaseWithCategory(int purchaseWithCategory) {
        this.purchaseWithCategory = purchaseWithCategory;
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(int purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }
}
