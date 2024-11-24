package knu.project.crm.outflux.dto;

import java.time.LocalDate;

public record CouponLogResponse(String gender, int age, LocalDate postDate, boolean isOutflux) {
}
