package knu.project.crm.product_recommend.controller;

import knu.project.crm.product_recommend.dto.ProductRecommendRequest;
import knu.project.crm.product_recommend.dto.ProductRecommendResponse;
import knu.project.crm.product_recommend.service.ProductRecommendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/recommend")
public class ProductRecommendController {
    private final ProductRecommendService productRecommendService;

    public ProductRecommendController(ProductRecommendService productRecommendService){
        this.productRecommendService = productRecommendService;
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<ProductRecommendResponse> getParameters(@PathVariable Long shopId){
        return ResponseEntity.ok(productRecommendService.getParmeters(shopId));
    }

    @PutMapping("/{shopId}")
    public ResponseEntity<?> updateParameters(@PathVariable Long shopId, @RequestBody ProductRecommendRequest productRecommendRequest){
        productRecommendService.updateParameters(shopId, productRecommendRequest);

        return ResponseEntity.ok("수정 완료.");
    }
}
