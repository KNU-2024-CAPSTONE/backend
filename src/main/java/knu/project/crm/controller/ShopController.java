package knu.project.crm.controller;

import knu.project.crm.entity.Shop;
import knu.project.crm.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    // 쇼핑몰 목록 조회 (GET)
    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        List<Shop> shops = shopService.getAllShops();
        return ResponseEntity.ok(shops);
    }

    // 쇼핑몰 추가 (PUT)
    @PutMapping
    public ResponseEntity<Shop> addShop(@RequestBody Shop shop) {
        Shop createdShop = shopService.addShop(shop);
        return ResponseEntity.ok(createdShop);
    }

    // 쇼핑몰 삭제 (DELETE)
    @DeleteMapping("/{shopId}")
    public ResponseEntity<String> deleteShop(@PathVariable Integer shopId) {
        shopService.deleteShop(shopId);
        return ResponseEntity.ok("Shop with ID " + shopId + " has been deleted.");
    }
}
