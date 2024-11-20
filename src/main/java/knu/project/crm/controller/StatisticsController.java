package knu.project.crm.controller;

import knu.project.crm.service.PurchaseLogService;
import knu.project.crm.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final PurchaseLogService purchaseLogService;

    public StatisticsController(StatisticsService statisticsService, PurchaseLogService purchaseLogService) {
        this.statisticsService = statisticsService;
        this.purchaseLogService = purchaseLogService;
    }

    @GetMapping("/subscribers")
    public ResponseEntity<Map<String, Map<String, Long>>> getSubscribersCountByDate(
            @RequestParam(defaultValue = "false") boolean isMonthly) {
        // 서비스로부터 가입자 수 데이터를 받아옴
        Map<String, Map<String, Long>> result = statisticsService.getSubscriberCountByDate(isMonthly);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales")
    public ResponseEntity<Map<String, ?>> getSales(@RequestParam boolean isWeekly) {
        if (isWeekly) {
            return ResponseEntity.ok(purchaseLogService.getMonthlyWeeklySales());
        } else {
            return ResponseEntity.ok(purchaseLogService.getMonthlySales());
        }
    }
}
