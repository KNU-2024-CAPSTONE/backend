package knu.project.crm;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CouponRequestDto {
    private Long memberId;
    private String category;
    private String code;

    // Getters and Setters
}
