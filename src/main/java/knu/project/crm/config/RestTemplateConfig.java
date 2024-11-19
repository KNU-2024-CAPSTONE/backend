package knu.project.crm.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() throws Exception {
        // SSLContext 생성
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(TrustAllStrategy.INSTANCE) // 모든 인증서 신뢰
                .build();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                        .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                                .setSslContext(SSLContextBuilder.create()
                                        .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                                        .build())
                                .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                .build())
                        .build())
                .build();

        // RestTemplate에 HttpClient 설정
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpclient);

        return new RestTemplate(requestFactory);
    }
}
