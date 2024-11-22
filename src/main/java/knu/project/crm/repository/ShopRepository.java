package knu.project.crm.repository;

import knu.project.crm.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Shop findByShopId(Integer shopId);
}
