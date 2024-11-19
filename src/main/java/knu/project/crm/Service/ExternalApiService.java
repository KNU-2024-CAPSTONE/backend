package knu.project.crm.Service;

import knu.project.crm.MemberLogDto;
import knu.project.crm.Entity.Shop;
import knu.project.crm.Repository.ShopRepository;
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

    public List<MemberLogDto> getMemberLogs(String shopId) {
        // ShopId로 데이터베이스에서 ShopUrl 조회
        String baseUrl = getBaseUrlByShopId(shopId);
        String url = baseUrl + "/member-log";

        ResponseEntity<MemberLogDto[]> response = restTemplate.getForEntity(url, MemberLogDto[].class);
        return List.of(response.getBody());
    }

    private String getBaseUrlByShopId(String shopId) {
        // 데이터베이스에서 Shop 조회
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found for ID: " + shopId));
        return shop.getShopUrl(); // ShopUrl 반환
    }
}
