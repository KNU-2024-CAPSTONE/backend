package knu.project.crm.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Shop {
    @Id
    String shopId;
    String shopName;
    String shopUrl;
    public String getShopUrl() {
        return this.shopUrl;
    }
}
