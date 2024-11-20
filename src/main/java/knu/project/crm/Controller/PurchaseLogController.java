package knu.project.crm.Controller;

import knu.project.crm.Entity.PurchaseLogDto;
import knu.project.crm.Service.ExternalApiService;
import knu.project.crm.Entity.MemberLogDto;
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
