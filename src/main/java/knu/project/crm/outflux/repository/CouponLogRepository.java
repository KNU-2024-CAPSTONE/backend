package knu.project.crm.outflux.repository;

import knu.project.crm.outflux.entity.CouponLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponLogRepository extends JpaRepository<CouponLog, Long> {
    List<CouponLog> findByShopId(Long shopId);
    List<CouponLog> findByShopIdAndMemberIdOrderByPostDateDesc(Long shopId, Long memberId);
}
