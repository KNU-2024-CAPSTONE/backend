package knu.project.crm.sale.controller;

import knu.project.crm.sale.dto.RecommendResultResponse;
import knu.project.crm.sale.dto.SaleForRecommendResponse;
import knu.project.crm.sale.dto.SaleResponse;
import knu.project.crm.sale.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customer/sale")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<List<SaleResponse>> getSale(@PathVariable Long shopId){
        return ResponseEntity.ok(saleService.getSale(shopId));
    }

    @GetMapping("/product/{shopId}")
    public ResponseEntity<List<SaleForRecommendResponse>> getPurchaseLogForSale(@PathVariable Long shopId){
        return ResponseEntity.ok(saleService.getPurchaseLogForSale(shopId));
    }

    @GetMapping("/recommend/{shopId}")
    public ResponseEntity<RecommendResultResponse> getRecommend(@PathVariable Long shopId){
        return ResponseEntity.ok(saleService.getRecommend(shopId));
    }
}
