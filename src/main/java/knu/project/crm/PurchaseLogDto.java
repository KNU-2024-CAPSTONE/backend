package knu.project.crm;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PurchaseLogDto {
    private String email;
    private String gender;
    private int age;
    private ProductDto product;
    private int quantity;
    private int starCount;
    private int totalPrice;
    private LocalDateTime purchaseTime;
    private boolean isRefund;

    // Getters and Setters
}

class ProductDto {
    private String category;
    private String name;
    private int price;

    // Getters and Setters
}
