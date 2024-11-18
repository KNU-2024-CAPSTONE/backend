package knu.project.crm;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8081/api/database";

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MemberLogDto> getMemberLogs() {
        String url = BASE_URL + "/member-log";
        ResponseEntity<MemberLogDto[]> response = restTemplate.getForEntity(url, MemberLogDto[].class);
        return List.of(response.getBody());
    }
}
