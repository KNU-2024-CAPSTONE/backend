package knu.project.crm.product_recommend.repository;

import knu.project.crm.product_recommend.entity.ProductRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRecommendRepository extends JpaRepository<ProductRecommend, Long> {
    Optional<ProductRecommend> findByShopId(Long shopId);
}
