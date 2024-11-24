package knu.project.crm.shop.service;

import knu.project.crm.common.exception.NotFoundException;
import knu.project.crm.shop.dto.ShopRequest;
import knu.project.crm.shop.entity.Shop;
import knu.project.crm.shop.repository.ShopRepository;
import knu.project.crm.shop.dto.ShopResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopService {
    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }

    @Transactional(readOnly = true)
    public List<ShopResponse> getShopList(){
        List<Shop> shopList = shopRepository.findAll();

        return shopList.stream().map(Shop::mapToResponse).toList();
    }

    @Transactional()
    public void addShop(ShopRequest shopRequest){
        Shop shop = new Shop(shopRequest.name(), shopRequest.uri());

        shopRepository.save(shop);
    }

    @Transactional
    public void deleteShop(Long shopId){
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰을 찾을 수 없습니다."));

        shopRepository.delete(shop);
    }
}
