package knu.project.crm.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class MemberLogDto {
    private String email;
    private String gender;
    private int age;
    private LocalDate registerDate;

    // Getters and Setters
}
