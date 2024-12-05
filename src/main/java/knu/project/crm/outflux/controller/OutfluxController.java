package knu.project.crm.outflux.controller;

import jakarta.mail.MessagingException;
import knu.project.crm.outflux.dto.*;
import knu.project.crm.outflux.service.OutfluxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/outflux")
public class OutfluxController {
    private final OutfluxService outfluxService;

    public OutfluxController(OutfluxService outfluxService){
        this.outfluxService = outfluxService;
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<OutfluxResponse> getParameters(@PathVariable Long shopId){
        return ResponseEntity.ok(outfluxService.getParameters(shopId));
    }

    @PutMapping("/{shopId}")
    public ResponseEntity<?> updateOutflux(@PathVariable Long shopId, @RequestBody OutfluxRequest outfluxRequest){
        outfluxService.updateOutfluxParameters(shopId, outfluxRequest);
        return ResponseEntity.ok("이탈 방지 수정 완료");
    }

    @PutMapping("/loyal/{shopId}")
    public ResponseEntity<?> updateLoyalCustomer(@PathVariable Long shopId, @RequestBody LoyalCustomerRequest loyalCustomerRequest){
        outfluxService.updateLoyalCustomerParameters(shopId, loyalCustomerRequest);
        return ResponseEntity.ok("충성 고객 수정 완료");
    }

    @GetMapping("/customers/{shopId}")
    public ResponseEntity<List<CouponLogResponse>> getLoyalAndOutfluxCustomers(@PathVariable Long shopId){
        return ResponseEntity.ok(outfluxService.getLoyalAndOutfluxCustomers(shopId));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() throws MessagingException {
        outfluxService.updateCouponLog();
        return ResponseEntity.ok("완료");
    }
}
