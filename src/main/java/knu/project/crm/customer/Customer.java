package knu.project.crm.customer;

import jakarta.persistence.*;
import knu.project.crm.sale.Sale;
import knu.project.crm.shop.Shop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private LocalDate registerDate;

    private String gender;

    @OneToOne
    private Shop shop;

    @OneToOne
    private Sale sale;
}