package knu.project.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Shop {
    @Id
    private String shopId;
    private String shopName;
    private String shopUrl;

    // Getter and Setter for shopId
    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    // Getter and Setter for shopName
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    // Getter and Setter for shopUrl
    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }
}
