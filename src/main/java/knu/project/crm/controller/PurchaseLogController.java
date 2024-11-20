package knu.project.crm.controller;

import knu.project.crm.dto.PurchaseLogDto;
import knu.project.crm.service.ExternalApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseLogController {

    private final ExternalApiService externalApiService;

    public PurchaseLogController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/api/purchase-log")
    public List<PurchaseLogDto> getPurchaseLogDto(@RequestParam String shopId) {
        // 클라이언트로부터 shopId를 받아 ExternalApiService 호출
        return externalApiService.getPurchaseLogDto(shopId);
    }
}
