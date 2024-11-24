package knu.project.crm.product_recommend.entity;

import jakarta.persistence.*;
import knu.project.crm.shop.entity.Shop;
import knu.project.crm.product_recommend.dto.ProductRecommendRequest;
import knu.project.crm.product_recommend.dto.ProductRecommendResponse;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ProductRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private boolean isStarCount;

    @NotNull
    private boolean isReview;

    @NotNull
    private boolean isPostDate;

    @NotNull
    private int k;

    @NotNull
    private int conversionRate;

    @OneToOne
    @JoinColumn(name = "shop_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Shop shop;

    public ProductRecommend(){}

    public ProductRecommend(boolean isStarCount, boolean isReview, boolean isPostDate, int k, int conversionRate, Shop shop){
        this.isStarCount = isStarCount;
        this.isReview = isReview;
        this.isPostDate = isPostDate;
        this.k = k;
        this.conversionRate = conversionRate;
        this.shop = shop;
    }

    public boolean getIsStarCount() {
        return isStarCount;
    }

    public boolean getIsReview(){
        return isReview;
    }

    public boolean getIsPostDate() {
        return isPostDate;
    }

    public int getK() {
        return k;
    }

    public int getConversionRate() {
        return conversionRate;
    }

    public Shop getShop() {
        return shop;
    }

    public ProductRecommendResponse mapToResponse(){
        return new ProductRecommendResponse(this.isStarCount, this.isReview, this.isPostDate, this.k, this.conversionRate);
    }

    public void updateParameters(ProductRecommendRequest productRecommendRequest) {
        this.isStarCount = productRecommendRequest.isStarCount();
        this.isReview = productRecommendRequest.isReview();
        this.isPostDate = productRecommendRequest.isPostDate();
        this.k = productRecommendRequest.k();
        this.conversionRate = productRecommendRequest.conversionRate();
    }
}
