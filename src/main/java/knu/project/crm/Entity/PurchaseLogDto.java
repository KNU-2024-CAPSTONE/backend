package knu.project.crm.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class PurchaseLogDto {

    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String gender;
    @Setter
    @Getter
    private int age;
    @Setter
    @Getter
    private Product product;
    @Setter
    @Getter
    private int quantity;
    @Setter
    @Getter
    private int starCount;
    @Setter
    @Getter
    private int totalPrice;
    @Setter
    @Getter
    private LocalDateTime purchaseTime;
    private boolean isRefund;

    public boolean isRefund() {
        return isRefund;
    }

    public void setRefund(boolean refund) {
        isRefund = refund;
    }
}
