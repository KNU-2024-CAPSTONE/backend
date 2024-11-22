package knu.project.crm.controller;

import knu.project.crm.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("shop/{shopId}/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/subscribers")
    public ResponseEntity<Map<String, Map<String, Map<String, Long>>>> getSubscribersCountByAgeGroup(
            @PathVariable Integer shopId, @RequestParam(defaultValue = "false") boolean isMonthly) {
        return ResponseEntity.ok(statisticsService.getSubscriberCountByAge(shopId, isMonthly));
    }

    @GetMapping("/sales")
    public ResponseEntity<Map<String, Map<String, Map<String, Integer>>>> getSalesStatisticsByAgeGroup(
            @PathVariable Integer shopId, @RequestParam(defaultValue = "false") boolean isMonthly) {
        return ResponseEntity.ok(statisticsService.getSalesStatisticsByAge(shopId, isMonthly));
    }
}
