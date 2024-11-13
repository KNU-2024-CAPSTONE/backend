package knu.project.crm.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public List<Shop> getAllShops() {
        return shopService.findAll();
    }

    @PostMapping
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        Shop savedShop = shopService.save(shop);
        return new ResponseEntity<>(savedShop, HttpStatus.CREATED);
    }

    @PutMapping("/{shopId}")
    public ResponseEntity<Shop> updateShop(@PathVariable Integer shopId, @RequestBody Shop shopDetails) {
        Optional<Shop> shopOptional = shopService.findById(shopId);
        if (shopOptional.isPresent()) {
            Shop shop = shopOptional.get();
            //shop.setName(shopDetails.getName());
            return ResponseEntity.ok(shopService.save(shop));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{shopId}")
    public ResponseEntity<Void> deleteShop(@PathVariable Integer shopId) {
        if (shopService.findById(shopId).isPresent()) {
            shopService.deleteById(shopId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
