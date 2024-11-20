package knu.project.crm.Controller;

import knu.project.crm.Service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/api/statistics/subscribers")
    public ResponseEntity<Map<String, Map<String, Long>>> getSubscribersCountByDate(
            @RequestParam(defaultValue = "false") boolean isMonthly) {
        // 서비스로부터 가입자 수 데이터를 받아옴
        Map<String, Map<String, Long>> result = statisticsService.getSubscriberCountByDate(isMonthly);

        return ResponseEntity.ok(result);
    }
}
