package knu.project.crm.outflux.dto;

import knu.project.crm.sale.dto.SaleForRecommendResponse;
import knu.project.crm.sale.dto.SaleResponse;

import java.time.LocalDateTime;

public class PurchaseLogRequest {
    String email;
    String gender;
    int age;
    Product product;
    int quantity;
    int starCount;
    int totalPrice;
    LocalDateTime purchaseTime;
    Boolean isRefund;

    public record Product(String category, String name, int price){}

    public PurchaseLogRequest(){}

    public PurchaseLogRequest(String email, String gender, int age, Product product, int quantity, int starCount, int totalPrice, LocalDateTime purchaseTime, Boolean isRefund){
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.product = product;
        this.quantity = quantity;
        this.starCount = starCount;
        this.totalPrice = totalPrice;
        this.purchaseTime = purchaseTime;
        this.isRefund = isRefund;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getStarCount() {
        return starCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getPurchaseTime() {
        return purchaseTime.toString();
    }

    public Boolean getIsRefund() {
        return isRefund;
    }

    public SaleForRecommendResponse.Product mapToRecommendResponseProduct(){
        return new SaleForRecommendResponse.Product(this.product.name, this.product.price);
    }

    public SaleResponse mapToResponse(){
        return new SaleResponse(this.gender, this.age, this.totalPrice, this.purchaseTime);
    }

    public SaleForRecommendResponse mapToRecommendResponse(){
        return new SaleForRecommendResponse(this.gender, this.age, mapToRecommendResponseProduct(), this.quantity);
    }
}
