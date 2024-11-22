package knu.project.crm.service;

import knu.project.crm.dto.MemberLogDto;
import knu.project.crm.dto.PurchaseLogDto;
import knu.project.crm.entity.Shop;
import knu.project.crm.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalApiService {

    private final ShopRepository shopRepository;

    public ExternalApiService(ShopRepository shopRepository, RestTemplate restTemplate) {
        this.shopRepository = shopRepository;
    }

    // shopId에 맞는 shopUrl을 가져오는 메서드
    String getShopUrlByShopId(Integer shopId) {
        // ShopId로 Shop 엔티티를 조회
        Shop shop = shopRepository.findByShopId(shopId);
        return shop.getShopUrl(); // shopUrl 반환
    }
}
