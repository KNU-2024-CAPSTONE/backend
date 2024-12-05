package knu.project.crm.common.service;

import knu.project.crm.outflux.dto.ai.*;
import knu.project.crm.shop.entity.Shop;
import knu.project.crm.influx.dto.MemberInfoRestResponse;
import knu.project.crm.outflux.dto.CouponRestResponse;
import knu.project.crm.outflux.dto.ProductListResponse;
import knu.project.crm.outflux.dto.ProductRecommendRequest;
import knu.project.crm.outflux.dto.PurchaseLogRequest;
import knu.project.crm.sale.dto.RecommendResultResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;
    private final String Ai_URI = "http://localhost:5000";
    private final String AI_RECOMMEND_PATH = "/api/ai/product-recommand";
    private final String AI_OUTFLUX_PATH = "/api/ai/outflux";
    private final URI PRODUCT_RECOMMEND_URI;
    private final URI OUTFLUX_URI;
    private final String MEMBERLOG_PATH = "/api/database/member-log";
    private final String PURCHASELOG_PATH = "/api/database/purchase-log";
    private final String PRODUCT_PATH = "/api/database/products";
    private final String RECOMMEND_PATH = "/api/database/recommend";
    private final String ADD_COUPON_PATH = "/api/database/add-coupon";


    public RestTemplateService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.PRODUCT_RECOMMEND_URI = UriComponentsBuilder.fromUriString(Ai_URI)
                .path(AI_RECOMMEND_PATH)
                .encode()
                .build()
                .toUri();
        this.OUTFLUX_URI = UriComponentsBuilder.fromUriString(Ai_URI)
                .path(AI_OUTFLUX_PATH)
                .encode()
                .build()
                .toUri();
    }

    public List<MemberInfoRestResponse> getMemberLog(Shop shop){
        URI baseUri = getBaseUri(shop, MEMBERLOG_PATH);

        return restTemplate.exchange(
                baseUri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MemberInfoRestResponse>>() {}
        ).getBody();
    }

    public List<ProductListResponse> getProductList(Shop shop){
        URI baseUri = getBaseUri(shop, PRODUCT_PATH);

        return restTemplate.exchange(
                baseUri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductListResponse>>() {}
        ).getBody();
    }

    public void postReceommendProduct(Shop shop, ProductRecommendRequest productRecommendRequest){
        URI baseUri = getBaseUri(shop, RECOMMEND_PATH);
        HttpEntity<ProductRecommendRequest> httpEntity = new HttpEntity<>(productRecommendRequest);

        restTemplate.exchange(
                baseUri,
                HttpMethod.POST,
                httpEntity,
                Void.class
        );
    }

    public RecommendResultResponse getRecommendProduct(Shop shop){
        URI baseUri = getBaseUri(shop, RECOMMEND_PATH);

        return restTemplate.exchange(
                baseUri,
                HttpMethod.GET,
                null,
                RecommendResultResponse.class
        ).getBody();
    }

    public List<PurchaseLogRequest> getPurchaseLog(Shop shop){
        URI baseUri = getBaseUri(shop, PURCHASELOG_PATH);

        return restTemplate.exchange(
                baseUri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PurchaseLogRequest>>() {}
        ).getBody();
    }

    public void postCoupon(Shop shop, AddCouponRequest addCouponRequest){
        URI baseUri = getBaseUri(shop, ADD_COUPON_PATH);
        HttpEntity<AddCouponRequest> httpEntity = new HttpEntity<>(addCouponRequest);

        restTemplate.exchange(
                baseUri,
                HttpMethod.POST,
                httpEntity,
                Void.class
        );
    }

    public List<PurchaseLogRequest> getPurchaseLogByMemberId(Shop shop, Long memberId){
        URI baseUri = getBaseUri(shop, PURCHASELOG_PATH, memberId);

        return restTemplate.exchange(
                baseUri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PurchaseLogRequest>>() {}
        ).getBody();
    }

    public void updateProductListWithRecommendForAi(RecommendProductUpdateRequest requestBody){
        HttpEntity<RecommendProductUpdateRequest> httpEntity = new HttpEntity<>(requestBody);

        restTemplate.exchange(
                PRODUCT_RECOMMEND_URI,
                HttpMethod.POST,
                httpEntity,
                Void.class
        );
    }

    public List<RecommendAiResponse> getRecommendForAi(RecommendAiRequest requestBody){
        HttpEntity<RecommendAiRequest> httpEntity = new HttpEntity<>(requestBody);

        return restTemplate.exchange(
                PRODUCT_RECOMMEND_URI,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<List<RecommendAiResponse>>() {}
        ).getBody();
    }

    public CouponRestResponse getCouponForAi(OutfluxAiRquest requestBody){
        HttpEntity<OutfluxAiRquest> httpEntity = new HttpEntity<>(requestBody);

        return restTemplate.exchange(
                        OUTFLUX_URI,
                        HttpMethod.POST,
                        httpEntity,
                        CouponRestResponse.class)
                .getBody();
    }

    public URI getBaseUri(Shop shop, String path){
        String uri = shop.getUri();

        return UriComponentsBuilder.fromUriString(uri)
                .path(path)
                .encode()
                .build()
                .toUri();
    }

    public URI getBaseUri(Shop shop, String path, Long memberId){
        String uri = shop.getUri();

        return UriComponentsBuilder.fromUriString(uri)
                .path(path + "/" + memberId.toString())
                .encode()
                .build()
                .toUri();
    }
}
