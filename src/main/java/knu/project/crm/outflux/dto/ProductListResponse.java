package knu.project.crm.outflux.dto;

import java.time.LocalDate;

public class ProductListResponse {
    private String productName;
    private Double averageStarCount;
    private Long review;
    private LocalDate postDate;

    public ProductListResponse(){}

    public ProductListResponse(String productName, Double averageStarCount, Long review, LocalDate postDate){
        this.productName = productName;
        this.averageStarCount = averageStarCount;
        this.review = review;
        this.postDate = postDate;
    }

    public String getProductName() {
        return productName;
    }

    public Double getAverageStarCount() {
        return averageStarCount;
    }

    public Long getReview() {
        return review;
    }

    public String getPostDate() {
        return postDate.toString();
    }
}
