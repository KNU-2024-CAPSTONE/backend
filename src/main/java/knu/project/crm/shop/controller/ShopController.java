package knu.project.crm.shop.controller;

import knu.project.crm.shop.dto.ShopRequest;
import knu.project.crm.shop.dto.ShopResponse;
import knu.project.crm.shop.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<List<ShopResponse>> getShopList(){
        return ResponseEntity.ok(shopService.getShopList());
    }

    @PostMapping
    public ResponseEntity<?> addShop(@RequestBody ShopRequest shopRequest){
        shopService.addShop(shopRequest);

        return ResponseEntity.ok("추가 성공");
    }

    @DeleteMapping("/{shopId}")
    public ResponseEntity<?> deleteShop(@PathVariable Long shopId){
        shopService.deleteShop(shopId);

        return ResponseEntity.ok("삭제 성공");
    }
}
