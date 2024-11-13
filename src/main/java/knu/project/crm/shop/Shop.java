package knu.project.crm.shop;

import jakarta.persistence.*;
import knu.project.crm.customer.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Shop")
@Getter @Setter
public class Shop {

    @Id
    private Integer shopId;
    private String name;

   @CreatedDate
    private LocalDateTime created_at;

   @LastModifiedDate
   private LocalDateTime updated_at;

   @OneToMany
   private List<Customer> customer_list;
}
