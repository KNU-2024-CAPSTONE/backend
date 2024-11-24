package knu.project.crm.sale.dto;

public record SaleForRecommendResponse(String gender, int age, Product product, int quantity) {
    public record Product(String name, int price){}
}
