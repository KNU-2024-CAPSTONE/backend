package knu.project.crm.service;

import knu.project.crm.entity.OutfluxAlgorithm;
import knu.project.crm.repository.OutfluxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutfluxService {

    @Autowired
    private OutfluxRepository outfluxRepository;

    public void saveOutfluxStatus(OutfluxAlgorithm outflux) {
        outfluxRepository.save(outflux);  // OutfluxAlgorithm 엔티티 저장
    }
}
