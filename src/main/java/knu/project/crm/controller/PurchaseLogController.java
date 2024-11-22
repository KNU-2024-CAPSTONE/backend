package knu.project.crm.controller;

import knu.project.crm.dto.PurchaseLogDto;
import knu.project.crm.service.ExternalApiService;
import knu.project.crm.service.PurchaseLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop")
public class PurchaseLogController {

    private final ExternalApiService externalApiService;
    private final PurchaseLogService purchaseLogService;

    public PurchaseLogController(ExternalApiService externalApiService, PurchaseLogService purchaseLogService) {
        this.externalApiService = externalApiService;
        this.purchaseLogService = purchaseLogService;
    }

    @GetMapping("statistics/{shopId}/purchase-log")
    public List<PurchaseLogDto> getPurchaseLogs(@PathVariable Integer shopId) {
        return purchaseLogService.getAllPurchaseLogs(shopId);
    }
}
