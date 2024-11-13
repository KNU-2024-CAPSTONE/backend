package knu.project.crm.sale;

import knu.project.crm.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleId;  // Sale ID

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;  // 고객 정보 (email, gender, age 등)

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Sale product;  // 제품 정보 (category, name, price)

    private int quantity;  // 구매 수량

    private int starCount;  // 별점 (1~5)

    private int totalPrice;  // 총 가격 (price * quantity)

    private boolean isRefund;  // 환불 여부 (true/false)

    @CreatedDate
    private LocalDateTime purchaseTime;  // 구매 시각

    // 생성자, 기타 필요한 메서드들
}
