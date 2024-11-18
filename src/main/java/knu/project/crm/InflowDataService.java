package knu.project.crm;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;
import java.util.Map;

@Service
public class InflowDataService {

    private final RestTemplate restTemplate;

    @Value("${external.api.url}") // 외부 API URL을 application.properties에 저장
    private String apiUrl;

    public InflowDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public InflowResponse getWeeklyInflowData(String shopid) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("shopid", shopid)
                .toUriString();

        return restTemplate.getForObject(url, InflowResponse.class);
    }
}
