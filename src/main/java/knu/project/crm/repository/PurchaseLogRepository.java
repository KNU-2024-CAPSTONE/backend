package knu.project.crm.repository;

import knu.project.crm.entity.PurchaseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseLogRepository extends JpaRepository<PurchaseLog, Long> {
    // 추가적인 쿼리 메서드를 작성할 수 있습니다.
}
