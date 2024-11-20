package knu.project.crm.dto;

import knu.project.crm.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseLogDto {

    private String email;
    private String gender;
    private int age;
    private Product product;
    private int quantity;
    private int starCount;
    private int totalPrice;
    private LocalDateTime purchaseTime;
    private boolean isRefund;
}
