package knu.project.crm.service;

import knu.project.crm.dto.PurchaseLogDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PurchaseLogService {

    private final RestTemplate restTemplate;

    public PurchaseLogService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 모든 구매 로그를 가져오는 메서드
    public List<PurchaseLogDto> getAllPurchaseLogs() {
        String url = "http://localhost:8081/api/database/purchase-log";
        PurchaseLogDto[] purchaseLogs = restTemplate.getForObject(url, PurchaseLogDto[].class);

        if (purchaseLogs == null) {
            throw new RuntimeException("Failed to fetch purchase logs");
        }

        return Arrays.asList(purchaseLogs);
    }
}
