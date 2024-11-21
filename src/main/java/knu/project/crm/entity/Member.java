package knu.project.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Member {

    @Id
    private Long id;
    private String email;
    private String gender;
    private int age;
    private LocalDate registerDate;

}
