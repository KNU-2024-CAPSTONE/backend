package knu.project.crm.service;

import knu.project.crm.dto.PurchaseLogDto;
import knu.project.crm.repository.MemberLogRepository;
import knu.project.crm.repository.PurchaseLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PurchaseLogService {

    private final RestTemplate restTemplate;
    private final PurchaseLogRepository purchaseLogRepository;
    private final ExternalApiService externalApiService;

    public PurchaseLogService(RestTemplate restTemplate,
                              PurchaseLogRepository purchaseLogRepository,
                              ExternalApiService externalApiService) {
        this.restTemplate = restTemplate;
        this.purchaseLogRepository = purchaseLogRepository;
        this.externalApiService = externalApiService;
    }

    // 모든 구매 로그를 가져오는 메서드
    public List<PurchaseLogDto> getAllPurchaseLogs(Integer shopId) {
        // ShopUrl을 shopId로 가져오기
        String shopUrl = externalApiService.getShopUrlByShopId(shopId);
        // 외부 API 호출
        String url = shopUrl + "/api/database/purchase-log";
        PurchaseLogDto[] purchaseLogs = restTemplate.getForObject(url, PurchaseLogDto[].class);

        if (purchaseLogs == null) {
            throw new RuntimeException("Failed to fetch purchase logs");
        }

        return Arrays.asList(purchaseLogs);
    }
}
