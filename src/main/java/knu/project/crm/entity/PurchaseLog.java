package knu.project.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class PurchaseLog {

    // getter, setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gender;
    private LocalDateTime purchaseTime;
    private int totalPrice;

    // 기본 생성자
    public PurchaseLog() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
