package knu.project.crm.outflux.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import knu.project.crm.shop.entity.Shop;
import knu.project.crm.outflux.dto.CouponLogResponse;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
public class CouponLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    Long memberId;

    @NotNull
    private String gender;

    @NotNull
    private int age;

    @NotNull
    private LocalDate postDate;

    @NotNull
    private boolean isOutflux;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Shop shop;

    public CouponLog(){}

    public CouponLog(Long memberId, String gender, int age, LocalDate postDate, boolean isOutflux, Shop shop){
        this.memberId = memberId;
        this.gender = gender;
        this.age = age;
        this.postDate = postDate;
        this.isOutflux = isOutflux;
        this.shop = shop;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public boolean getIsOutflux() {
        return isOutflux;
    }

    public Shop getShop() {
        return shop;
    }

    public CouponLogResponse mapToResponse(){
        return new CouponLogResponse(this.gender, this.age, this.postDate, this.isOutflux);
    }
}
