package knu.project.crm.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import knu.project.crm.shop.dto.ShopResponse;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String name;

    @NotNull
    String uri;

    public Shop(){}

    public Shop(String name, String uri){
        this.name = name;
        this.uri = uri;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public ShopResponse mapToResponse(){
        return new ShopResponse(this.id, this.name);
    }
}
