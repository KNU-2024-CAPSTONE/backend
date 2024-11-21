package knu.project.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Shop {
    // Getter and Setter for shopId
    @Id
    private String shopId;
    // Getter and Setter for shopName
    private String shopName;
    // Getter and Setter for shopUrl
    private String shopUrl;

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }
}
