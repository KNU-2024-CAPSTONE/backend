package knu.project.crm.controller;

import knu.project.crm.entity.Member;
import knu.project.crm.entity.OutfluxAlgorithm;
import knu.project.crm.dto.AccessLogDto;
import knu.project.crm.service.MemberLogService;
import knu.project.crm.service.OutfluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("shop/{shopId}/outflux")
public class OutfluxController {

    @Autowired
    private OutfluxService outfluxService;

    @Autowired
    private MemberLogService memberLogService;  // MemberService를 주입하여 이메일로 회원 정보 조회

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String ACCESS_LOG_API_URL = "https://external-api.com/api/database/access-log";

    //db 연결 부분은 일단 나중에
//    @GetMapping("/check-outflux")
//    public void checkAndSaveOutfluxCustomers() {
//        // 외부 API에서 접속 기록을 가져오기
//        List<AccessLogDto> accessLogs = fetchAccessLogsFromApi();
//
//        // 고객 이탈 여부 판단 및 DB에 저장
//        for (AccessLogDto accessLog : accessLogs) {
//            boolean isOutflux = isCustomerOutflux(accessLog.getAccessTime());
//            saveOutfluxStatus(accessLog, isOutflux);
//        }
//    }

    private List<AccessLogDto> fetchAccessLogsFromApi() {
        return restTemplate.getForObject(ACCESS_LOG_API_URL, List.class);
    }

    private boolean isCustomerOutflux(LocalDateTime lastAccessTime) {
        // 예: 마지막 접속이 6개월 이상 전이라면 이탈로 판단
        return lastAccessTime.isBefore(LocalDateTime.now().minusMonths(6));
    }

    private void saveOutfluxStatus(AccessLogDto accessLog, boolean isOutflux) {
        Member member = memberLogService.findByEmail(accessLog.getEmail());  // 이메일로 회원 조회

        if (member != null) {
            OutfluxAlgorithm outflux = new OutfluxAlgorithm();
            outflux.setMember(member);  // Member 객체 설정
            outflux.setLastAccessTime(accessLog.getAccessTime());
            outflux.setIsOutflux(isOutflux);

            outfluxService.saveOutfluxStatus(outflux);
        }
    }
}
