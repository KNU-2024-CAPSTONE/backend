package knu.project.crm;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddCouponService {

    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://localhost:8081/api/database";

    public AddCouponService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String addCoupon(CouponRequestDto requestDto) {
        String url = BASE_URL + "/add-coupon";

        // HTTP Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // HTTP 요청 생성
        HttpEntity<CouponRequestDto> entity = new HttpEntity<>(requestDto, headers);

        // POST 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // 응답 반환
        return response.getBody();
    }
}
