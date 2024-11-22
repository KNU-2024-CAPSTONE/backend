package knu.project.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Shop")
public class Shop {
    // Getter and Setter for shopId
    @Id
    @GeneratedValue
    private Integer shopId;
    // Getter and Setter for shopName
    private String shopName;
    // Getter and Setter for shopUrl
    private String shopUrl;

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }
}
