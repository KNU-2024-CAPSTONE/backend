package knu.project.crm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  // @Controller -> @RestController로 변경
public class ApiController {

    private final ExternalApiService externalApiService;

    public ApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/fetch-members")
    public List<MemberLogDto> fetchMemberLogs() {
        return externalApiService.getMemberLogs();
    }

    // 필요한 경우 아래 메서드도 활성화
//    @GetMapping("/fetch-purchase-logs")
//    public List<PurchaseLogDto> fetchPurchaseLogs() {
//        return externalApiService.getPurchaseLogs();
//    }
}
