package knu.project.crm.Controller;

import knu.project.crm.Service.ExternalApiService;
import knu.project.crm.Entity.MemberLogDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberLogController {

    private final ExternalApiService externalApiService;

    public MemberLogController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/api/member-log")
    public List<MemberLogDto> getMemberLogs(@RequestParam String shopId) {
        // 클라이언트로부터 shopId를 받아 ExternalApiService 호출
        return externalApiService.getMemberLogs(shopId);
    }
}
