package knu.project.crm.service;

import knu.project.crm.entity.Shop;
import knu.project.crm.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    // 쇼핑몰 목록 조회
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    // 쇼핑몰 추가
    public Shop addShop(Shop shop) {
        return shopRepository.save(shop);
    }

    // 쇼핑몰 삭제
    public void deleteShop(Integer shopId) {
        if (!shopRepository.existsById(shopId)) {
            throw new IllegalArgumentException("Shop with ID " + shopId + " does not exist.");
        }
        shopRepository.deleteById(shopId);
    }
}
