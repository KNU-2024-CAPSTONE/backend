package knu.project.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;

    private String category;
    private String name;
    private double price;
}
