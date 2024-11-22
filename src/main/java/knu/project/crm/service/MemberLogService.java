package knu.project.crm.service;

import knu.project.crm.dto.MemberLogDto;
import knu.project.crm.entity.Member;
import knu.project.crm.repository.MemberLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class MemberLogService {

    private final RestTemplate restTemplate;
    private final MemberLogRepository memberLogRepository;
    private final ExternalApiService externalApiService;

    // 생성자 주입 방식으로 필드를 주입
    public MemberLogService(RestTemplate restTemplate,
                            MemberLogRepository memberLogRepository,
                            ExternalApiService externalApiService) {
        this.restTemplate = restTemplate;
        this.memberLogRepository = memberLogRepository;
        this.externalApiService = externalApiService;
    }

    // 이메일로 회원 조회
    public Member findByEmail(String email) {
        return memberLogRepository.findByEmail(email);
    }

    // 모든 회원 로그를 가져오는 메서드
    public List<MemberLogDto> getAllMemberLogs(Integer shopId) {  // shopId를 매개변수로 받음
        // ExternalApiService를 통해 ShopUrl을 가져오기
        String shopUrl = externalApiService.getShopUrlByShopId(shopId);
        // 외부 API 호출
        String url = shopUrl + "/api/database/member-log";
        MemberLogDto[] memberLogs = restTemplate.getForObject(url, MemberLogDto[].class);

        if (memberLogs == null) {
            throw new RuntimeException("Failed to fetch member logs");
        }

        return Arrays.asList(memberLogs);
    }
}
