package knu.project.crm.sale.dto;

import java.time.LocalDateTime;

public record SaleResponse(String gender, int age, int totalPrice, LocalDateTime purchaseTime) {
}
