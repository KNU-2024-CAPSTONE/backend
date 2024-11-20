package knu.project.crm.service;

import knu.project.crm.dto.PurchaseLogDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendService {
    private final RestTemplate restTemplate;

    public RecommendService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Long> getPopularCategoriesByGenderAndAge(String gender, int minAge, int maxAge) {
        // 외부 API 호출 (구매 정보 가져오기)
        String url = "http://localhost:8081/api/database/purchase-log";
        PurchaseLogDto[] purchaseLogs = restTemplate.getForObject(url, PurchaseLogDto[].class);

        if (purchaseLogs == null) {
            throw new RuntimeException("Failed to fetch purchase logs");
        }

        // 데이터 필터링: 성별 및 연령대
        List<PurchaseLogDto> filteredLogs = Arrays.stream(purchaseLogs)
                .filter(log -> log.getGender().equalsIgnoreCase(gender))
                .filter(log -> log.getAge() >= minAge && log.getAge() <= maxAge)
                .filter(log -> !log.isRefund()) // 환불 제외
                .collect(Collectors.toList());

        // 카테고리별 구매 횟수 집계
        Map<String, Long> categoryCounts = filteredLogs.stream()
                .collect(Collectors.groupingBy(
                        log -> log.getProduct().getCategory(),
                        Collectors.counting()
                ));

        // 카테고리 순위 정렬
        return categoryCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
