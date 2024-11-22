package knu.project.crm.controller;

import knu.project.crm.dto.MemberLogDto;
import knu.project.crm.service.ExternalApiService;
import knu.project.crm.service.MemberLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("shop")
public class MemberLogController {

    private final ExternalApiService externalApiService;
    private final MemberLogService memberLogService;

    public MemberLogController(ExternalApiService externalApiService, MemberLogService memberLogService) {
        this.externalApiService = externalApiService;
        this.memberLogService = memberLogService;
    }

    @GetMapping("statistics/{shopId}/member-log")
    public List<MemberLogDto> getMemberLogs(@PathVariable Integer shopId) {
        return memberLogService.getAllMemberLogs(shopId);
    }

}
