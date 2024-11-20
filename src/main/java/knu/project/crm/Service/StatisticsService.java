package knu.project.crm.Service;

import knu.project.crm.Entity.MemberLogDto;
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

    // 일별 / 월별 가입자 수를 계산하는 메서드
    public Map<String, Map<String, Long>> getSubscriberCountByDate(boolean isMonthly) {
        String url = "http://localhost:8081/api/database/member-log";
        MemberLogDto[] memberLogs = restTemplate.getForObject(url, MemberLogDto[].class);

        if (memberLogs == null) {
            throw new RuntimeException("Failed to fetch member logs");
        }

        // 일별 또는 월별로 가입자 수를 그룹화
        Map<String, Map<String, Long>> groupedData = Arrays.stream(memberLogs)
                .collect(Collectors.groupingBy(
                        log -> isMonthly ? getMonthAndYear(log.getRegisterDate()) : getDate(log.getRegisterDate()),
                        Collectors.groupingBy(MemberLogDto::getGender, Collectors.counting())
                ));

        // 날짜/월 순서대로 결과 정렬
        if (isMonthly) {
            return sortByMonth(groupedData);
        } else {
            return sortByDay(groupedData);
        }
    }

    // 월별로 정렬하여 출력
    private Map<String, Map<String, Long>> sortByMonth(Map<String, Map<String, Long>> data) {
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

    // 일별로 정렬하여 출력
    private Map<String, Map<String, Long>> sortByDay(Map<String, Map<String, Long>> data) {
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

    // 월별 형식으로 데이터를 가져오는 메서드
    private String getMonthAndYear(LocalDate date) {
        return date.getYear() + "-" + String.format("%02d", date.getMonthValue());
    }

    // 일별 형식으로 데이터를 가져오는 메서드
    private String getDate(LocalDate date) {
        return date.toString(); // "YYYY-MM-DD" 형식으로 반환
    }
}
