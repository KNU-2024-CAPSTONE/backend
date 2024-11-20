package knu.project.crm.service;

import knu.project.crm.dto.MemberLogDto;
import knu.project.crm.dto.PurchaseLogDto;
import knu.project.crm.entity.Shop;
import knu.project.crm.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;
    private final ShopRepository shopRepository;

    public ExternalApiService(RestTemplate restTemplate, ShopRepository shopRepository) {
        this.restTemplate = restTemplate;
        this.shopRepository = shopRepository;
    }

    private String getBaseUrlByShopId(String shopId) {
        // 데이터베이스에서 Shop 조회
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found for ID: " + shopId));
        return shop.getShopUrl(); // ShopUrl 반환
    }

    public List<MemberLogDto> getMemberLogs(String shopId) {
        // ShopId로 데이터베이스에서 ShopUrl 조회
        // String baseUrl = getBaseUrlByShopId(shopId);
        String baseUrl = "http://localhost:8081";
        String url = baseUrl + "/api/database/member-log";

        // ResponseEntity<List<MemberLogDto>>로 응답을 받음
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        // 응답 본문에서 데이터 추출하여 반환
        return response.getBody();
    }

    public List<PurchaseLogDto> getPurchaseLogDto(String shopId) {
        // ShopId로 데이터베이스에서 ShopUrl 조회
        // String baseUrl = getBaseUrlByShopId(shopId);
        String baseUrl = "http://localhost:8081";
        String url = baseUrl + "/api/database/purchase-log";

        // ResponseEntity<List<MemberLogDto>>로 응답을 받음
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        // 응답 본문에서 데이터 추출하여 반환
        return response.getBody();
    }
}
