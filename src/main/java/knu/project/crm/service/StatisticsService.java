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

    public StatisticsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 월별 또는 일별 가입자 수를 계산하는 메서드
    public Map<String, Map<String, Long>> getSubscriberCount(boolean isMonthly) {
        String url = "http://localhost:8081/api/database/member-log";
        MemberLogDto[] memberLogs = restTemplate.getForObject(url, MemberLogDto[].class);

        if (memberLogs == null) {
            throw new RuntimeException("Failed to fetch member logs");
        }

        // 월별 또는 일별로 가입자 수를 그룹화
        Map<String, Map<String, Long>> groupedData = Arrays.stream(memberLogs)
                .collect(Collectors.groupingBy(
                        log -> isMonthly ? getMonthAndYear(log.getRegisterDate()) : getDate(log.getRegisterDate()),
                        Collectors.groupingBy(MemberLogDto::getGender, Collectors.counting())
                ));

        // 날짜/월 순서대로 결과 정렬
        return isMonthly ? sortByMonthForSubscribers(groupedData) : sortByDayForSubscribers(groupedData);
    }

    // 월별 또는 일별 매출액을 계산하는 메서드
    public Map<String, Map<String, Integer>> getSalesStatistics(boolean isMonthly) {
        String url = "http://localhost:8081/api/database/purchase-log";
        PurchaseLogDto[] purchaseLogs = restTemplate.getForObject(url, PurchaseLogDto[].class);

        if (purchaseLogs == null) {
            throw new RuntimeException("Failed to fetch purchase logs");
        }

        // 월별 또는 일별로 매출액을 그룹화
        Map<String, Map<String, Integer>> groupedData = Arrays.stream(purchaseLogs)
                .collect(Collectors.groupingBy(
                        log -> isMonthly ? getMonthAndYear(log.getPurchaseTime().toLocalDate()) : getDate(log.getPurchaseTime().toLocalDate()),
                        Collectors.groupingBy(PurchaseLogDto::getGender,
                                Collectors.summingInt(PurchaseLogDto::getTotalPrice))
                ));

        // 날짜/월 순서대로 결과 정렬
        return isMonthly ? sortByMonthForSales(groupedData) : sortByDayForSales(groupedData);
    }

    // 월별 가입자 수 정렬하여 출력
    private Map<String, Map<String, Long>> sortByMonthForSubscribers(Map<String, Map<String, Long>> data) {
        Map<String, Map<String, Long>> sortedData = new LinkedHashMap<>();
        // 1월부터 12월까지 순서대로 설정
        List<String> months = Arrays.asList(
                "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06",
                "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12"
        );

        for (String month : months) {
            Map<String, Long> genderMap = data.getOrDefault(month, new HashMap<>());
            Map<String, Long> genderCountMap = new HashMap<>();
            // 성별 (female, male) 순서로 설정
            for (String gender : Arrays.asList("female", "male")) {
                genderCountMap.put(gender, genderMap.getOrDefault(gender, 0L));
            }
            sortedData.put(month, genderCountMap);
        }
        return sortedData;
    }

    // 일별 가입자 수 정렬하여 출력
    private Map<String, Map<String, Long>> sortByDayForSubscribers(Map<String, Map<String, Long>> data) {
        Map<String, Map<String, Long>> sortedData = new LinkedHashMap<>();

        // 1월 1일부터 12월 31일까지 날짜를 모두 포함
        for (int month = 1; month <= 12; month++) {
            // 1일부터 31일까지 모든 날짜에 대해 0으로 초기화
            for (int day = 1; day <= 31; day++) {
                String date = String.format("2024-%02d-%02d", month, day);
                Map<String, Long> genderMap = data.getOrDefault(date, new HashMap<>());
                Map<String, Long> genderCountMap = new HashMap<>();
                // 성별 (female, male) 순서로 설정
                for (String gender : Arrays.asList("female", "male")) {
                    genderCountMap.put(gender, genderMap.getOrDefault(gender, 0L));
                }
                sortedData.put(date, genderCountMap);
            }
        }

        return sortedData;
    }

    // 월별 매출액 정렬하여 출력
    private Map<String, Map<String, Integer>> sortByMonthForSales(Map<String, Map<String, Integer>> data) {
        Map<String, Map<String, Integer>> sortedData = new LinkedHashMap<>();
        // 1월부터 12월까지 순서대로 설정
        List<String> months = Arrays.asList(
                "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06",
                "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12"
        );

        for (String month : months) {
            Map<String, Integer> genderMap = data.getOrDefault(month, new HashMap<>());
            Map<String, Integer> genderSalesMap = new HashMap<>();
            // 성별 (female, male) 순서로 설정
            for (String gender : Arrays.asList("female", "male")) {
                genderSalesMap.put(gender, genderMap.getOrDefault(gender, 0));
            }
            sortedData.put(month, genderSalesMap);
        }
        return sortedData;
    }

    // 일별 매출액 정렬하여 출력
    private Map<String, Map<String, Integer>> sortByDayForSales(Map<String, Map<String, Integer>> data) {
        Map<String, Map<String, Integer>> sortedData = new LinkedHashMap<>();

        // 1월 1일부터 12월 31일까지 날짜를 모두 포함
        for (int month = 1; month <= 12; month++) {
            // 1일부터 31일까지 모든 날짜에 대해 0으로 초기화
            for (int day = 1; day <= 31; day++) {
                String date = String.format("2024-%02d-%02d", month, day);
                Map<String, Integer> genderMap = data.getOrDefault(date, new HashMap<>());
                Map<String, Integer> genderSalesMap = new HashMap<>();
                // 성별 (female, male) 순서로 설정
                for (String gender : Arrays.asList("female", "male")) {
                    genderSalesMap.put(gender, genderMap.getOrDefault(gender, 0));
                }
                sortedData.put(date, genderSalesMap);
            }
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
