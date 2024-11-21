package knu.project.crm.repository;

import knu.project.crm.entity.OutfluxAlgorithm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutfluxRepository extends JpaRepository<OutfluxAlgorithm, Long> {
}
