package knu.project.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {  // 클래스 이름 변경

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
