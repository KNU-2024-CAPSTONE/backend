package knu.project.crm.service;

import knu.project.crm.entity.PurchaseLog;
import knu.project.crm.repository.PurchaseLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseLogService {
    private final PurchaseLogRepository purchaseLogRepository;

    public PurchaseLogService(PurchaseLogRepository purchaseLogRepository) {
        this.purchaseLogRepository = purchaseLogRepository;
    }

    // 월별 매출 (성별 제외)
    public Map<String, Integer> getMonthlySales() {
        List<PurchaseLog> purchaseLogs = purchaseLogRepository.findAll();

        // 월별 매출 계산 (성별 제외)
        Map<String, Integer> monthlySales = purchaseLogs.stream()
                .filter(p -> p.getPurchaseTime() != null)
                .collect(Collectors.groupingBy(
                        p -> p.getPurchaseTime().getYear() + "-" + String.format("%02d", p.getPurchaseTime().getMonthValue()),
                        Collectors.summingInt(PurchaseLog::getTotalPrice)
                ));

        return monthlySales;
    }

    public Map<String, Map<String, Integer>> getMonthlyWeeklySales() {
        Map<String, Map<String, Integer>> weeklySales = new HashMap<>();

        // 주별 매출 계산 (예시)
        for (int week = 1; week <= 4; week++) {  // 예시로 4주차로 설정
            Map<String, Integer> salesForWeek = new HashMap<>();
            salesForWeek.put("male", calculateSalesForWeekAndGender(week, "male"));
            salesForWeek.put("female", calculateSalesForWeekAndGender(week, "female"));

            weeklySales.put(week + "주차", salesForWeek);
        }

        return weeklySales;
    }

    // 주별, 성별 매출을 계산하는 메서드 (예시)
    private int calculateSalesForWeekAndGender(int week, String gender) {
        // 실제 계산 로직을 구현 (예시로 랜덤 값 리턴)
        return new Random().nextInt(100000);  // 예시로 랜덤 매출값
    }

    private int getWeekNumber(LocalDateTime purchaseTime) {
        int dayOfMonth = purchaseTime.getDayOfMonth();

        if (dayOfMonth <= 7) {
            return 1;
        } else if (dayOfMonth <= 14) {
            return 2;
        } else if (dayOfMonth <= 21) {
            return 3;
        } else {
            return 4;
        }
    }
}
