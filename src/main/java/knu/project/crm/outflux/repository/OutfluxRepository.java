package knu.project.crm.outflux.repository;

import knu.project.crm.outflux.entity.Outflux;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OutfluxRepository extends JpaRepository<Outflux, Long> {
    Optional<Outflux> findByShopId(Long shopId);
}
