package knu.project.crm.service;

import knu.project.crm.dto.MemberLogDto;
import knu.project.crm.dto.PurchaseLogDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final RestTemplate restTemplate;
    private final ExternalApiService externalApiService;

    public StatisticsService(RestTemplate restTemplate, ExternalApiService externalApiService) {
        this.restTemplate = restTemplate;
        this.externalApiService = externalApiService;
    }

    private final List<String> ageGroups = Arrays.asList("10대", "20대", "30대", "40대", "50대", "60대 이상");

    // 나이대 정의 메서드
    private String getAgeGroup(int age) {
        if (age < 20) return "10대";
        if (age < 30) return "20대";
        if (age < 40) return "30대";
        if (age < 50) return "40대";
        if (age < 60) return "50대";
        return "60대 이상";
    }

    // 월별 또는 일별 가입자 수를 계산하는 메서드 (나이대와 성별 포함)
    public Map<String, Map<String, Map<String, Long>>> getSubscriberCountByAge(Integer shopId, boolean isMonthly) {
        // ShopUrl을 shopId로 가져오기
        String shopUrl = externalApiService.getShopUrlByShopId(shopId);
        // 외부 API 호출
        String url = shopUrl + "/api/database/member-log";        MemberLogDto[] memberLogs = restTemplate.getForObject(url, MemberLogDto[].class);

        if (memberLogs == null) {
            throw new RuntimeException("Failed to fetch member logs");
        }

        Map<String, Map<String, Map<String, Long>>> groupedData = Arrays.stream(memberLogs)
                .collect(Collectors.groupingBy(
                        log -> isMonthly ? getMonthAndYear(log.getRegisterDate()) : getDate(log.getRegisterDate()),
                        Collectors.groupingBy(log -> getAgeGroup(log.getAge()),
                                Collectors.groupingBy(MemberLogDto::getGender, Collectors.counting()))
                ));

        return isMonthly ? sortByMonth(groupedData) : sortByDay(groupedData);
    }

    // 월별 또는 일별 매출액을 계산하는 메서드 (나이대와 성별 포함)
    public Map<String, Map<String, Map<String, Integer>>> getSalesStatisticsByAge(Integer shopId, boolean isMonthly) {
        // ShopUrl을 shopId로 가져오기
        String shopUrl = externalApiService.getShopUrlByShopId(shopId);
        // 외부 API 호출
        String url = shopUrl + "/api/database/purchase-log";
        PurchaseLogDto[] purchaseLogs = restTemplate.getForObject(url, PurchaseLogDto[].class);

        if (purchaseLogs == null) {
            throw new RuntimeException("Failed to fetch purchase logs");
        }

        Map<String, Map<String, Map<String, Integer>>> groupedData = Arrays.stream(purchaseLogs)
                .collect(Collectors.groupingBy(
                        log -> isMonthly ? getMonthAndYear(log.getPurchaseTime().toLocalDate()) : getDate(log.getPurchaseTime().toLocalDate()),
                        Collectors.groupingBy(log -> getAgeGroup(log.getAge()),
                                Collectors.groupingBy(PurchaseLogDto::getGender,
                                        Collectors.summingInt(PurchaseLogDto::getTotalPrice)))
                ));

        return isMonthly ? sortByMonth(groupedData) : sortByDay(groupedData);
    }

    // 월별 데이터 정렬
    private <T> Map<String, Map<String, Map<String, T>>> sortByMonth(Map<String, Map<String, Map<String, T>>> data) {
        Map<String, Map<String, Map<String, T>>> sortedData = new LinkedHashMap<>();
        List<String> months = Arrays.asList(
                "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06",
                "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12"
        );

        for (String month : months) {
            sortedData.put(month, sortByAgeGroup(data.getOrDefault(month, new HashMap<>())));
        }

        return sortedData;
    }

    // 일별 데이터 정렬
    private <T> Map<String, Map<String, Map<String, T>>> sortByDay(Map<String, Map<String, Map<String, T>>> data) {
        Map<String, Map<String, Map<String, T>>> sortedData = new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {
            for (int day = 1; day <= 31; day++) {
                String date = String.format("2024-%02d-%02d", month, day);
                sortedData.put(date, sortByAgeGroup(data.getOrDefault(date, new HashMap<>())));
            }
        }

        return sortedData;
    }

    // 나이대 정렬
    private <T> Map<String, Map<String, T>> sortByAgeGroup(Map<String, Map<String, T>> data) {
        Map<String, Map<String, T>> sortedData = new LinkedHashMap<>();

        for (String ageGroup : ageGroups) {
            sortedData.put(ageGroup, data.getOrDefault(ageGroup, new HashMap<>()));
        }

        return sortedData;
    }

    // 월별 형식으로 데이터를 가져오는 메서드
    private String getMonthAndYear(LocalDate date) {
        return date.getYear() + "-" + String.format("%02d", date.getMonthValue());
    }

    // 일별 형식으로 데이터를 가져오는 메서드
    private String getDate(LocalDate date) {
        return date.toString(); // "YYYY-MM-DD" 형식으로 반환
    }
}
