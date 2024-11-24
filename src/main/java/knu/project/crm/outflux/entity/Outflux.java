package knu.project.crm.outflux.entity;

import jakarta.persistence.*;
import knu.project.crm.shop.entity.Shop;
import knu.project.crm.outflux.dto.LoyalCustomerRequest;
import knu.project.crm.outflux.dto.OutfluxRequest;
import knu.project.crm.outflux.dto.OutfluxResponse;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Outflux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int lastPurchase;

    @NotNull
    private int lastRefund;

    @NotNull
    private int refundPercent;

    @NotNull
    private int reIssue;

    @NotNull
    private int purchaseWithCategory;

    @NotNull
    private int purchaseNumber;

    @OneToOne
    @JoinColumn(name = "shop_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Shop shop;

    public Outflux() {}

    public Outflux(int lastPurchase, int lastRefund, int refundPercent, int reIssue, int purchaseWithCategory, int purchaseNumber, Shop shop){
        this.lastPurchase = lastPurchase;
        this.lastRefund = lastRefund;
        this.refundPercent = refundPercent;
        this.reIssue = reIssue;
        this.purchaseWithCategory = purchaseWithCategory;
        this.purchaseNumber = purchaseNumber;
        this.shop = shop;
    }

    public Long getId() {
        return id;
    }

    public int getLastPurchase() {
        return lastPurchase;
    }

    public int getLastRefund() {
        return lastRefund;
    }

    public int getRefundPercent() {
        return refundPercent;
    }

    public int getReIssue() {
        return reIssue;
    }

    public int getPurchaseWithCategory() {
        return purchaseWithCategory;
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public Shop getShop() {
        return shop;
    }

    public OutfluxResponse mapToResponse(){
        return new OutfluxResponse(this.lastPurchase, this.lastRefund, this.refundPercent, this.reIssue, this.purchaseWithCategory, this.purchaseNumber);
    }

    public void updateOutflux(OutfluxRequest outfluxRequest){
        this.lastPurchase = outfluxRequest.lastPurchase();
        this.lastRefund = outfluxRequest.lastRefund();
        this.refundPercent = outfluxRequest.refundPercent();
        this.reIssue = outfluxRequest.reIssue();
    }

    public void updateLoyalCustomer(LoyalCustomerRequest loyalCustomerRequest){
        this.purchaseWithCategory = loyalCustomerRequest.purchaseWithCategory();
        this.purchaseNumber = loyalCustomerRequest.purchaseNumber();
    }
}
