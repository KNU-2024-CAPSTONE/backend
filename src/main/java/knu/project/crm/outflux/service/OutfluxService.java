package knu.project.crm.outflux.service;

import knu.project.crm.outflux.dto.ai.*;
import knu.project.crm.shop.entity.Shop;
import knu.project.crm.common.exception.NotFoundException;
import knu.project.crm.shop.repository.ShopRepository;
import knu.project.crm.common.service.RestTemplateService;
import knu.project.crm.influx.dto.MemberInfoRestResponse;
import knu.project.crm.outflux.dto.*;
import knu.project.crm.outflux.entity.CouponLog;
import knu.project.crm.outflux.entity.Outflux;
import knu.project.crm.outflux.repository.CouponLogRepository;
import knu.project.crm.outflux.repository.OutfluxRepository;
import knu.project.crm.product_recommend.entity.ProductRecommend;
import knu.project.crm.product_recommend.repository.ProductRecommendRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OutfluxService {
    private final ShopRepository shopRepository;
    private final OutfluxRepository outfluxRepository;
    private final CouponLogRepository couponLogRepository;
    private final ProductRecommendRepository productRecommendRepository;
    private final RestTemplateService restTemplateService;

    public OutfluxService(ShopRepository shopRepository, OutfluxRepository outfluxRepository, CouponLogRepository couponLogRepository, ProductRecommendRepository productRecommendRepository, RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
        this.shopRepository = shopRepository;
        this.outfluxRepository = outfluxRepository;
        this.couponLogRepository = couponLogRepository;
        this.productRecommendRepository = productRecommendRepository;
    }

    @Transactional(readOnly = true)
    public OutfluxResponse getParameters(Long shopId){
        Outflux outflux = outfluxRepository.findByShopId(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰에 해당하는 이탈방지 파라미터 값을 찾을 수 없습니다."));

        return outflux.mapToResponse();
    }

    @Transactional
    public void updateOutfluxParameters(Long shopId, OutfluxRequest outfluxRequest){
        Outflux outflux = outfluxRepository.findByShopId(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰에 해당하는 이탈방지 파라미터 값을 찾을 수 없습니다."));
        outflux.updateOutflux(outfluxRequest);
    }

    @Transactional
    public void updateLoyalCustomerParameters(Long shopId, LoyalCustomerRequest loyalCustomerRequest){
        Outflux outflux = outfluxRepository.findByShopId(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰에 해당하는 이탈방지 파라미터 값을 찾을 수 없습니다."));
        outflux.updateLoyalCustomer(loyalCustomerRequest);
    }

    @Transactional(readOnly = true)
    public List<CouponLogResponse> getLoyalAndOutfluxCustomers(Long shopId){
        List<CouponLog> couponLogList = couponLogRepository.findByShopId(shopId);

        return couponLogList.stream().map(CouponLog::mapToResponse).toList();
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void updateRecommend(){
        List<Shop> shopList = shopRepository.findAll();

        for(Shop shop: shopList){
            Long shopId = shop.getId();
            ProductRecommend productRecommend = productRecommendRepository.findByShopId(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰에 해당하는 상품추천 파라미터 값을 찾을 수 없습니다."));

            //사용자 조회
            List<MemberInfoRestResponse> memberInfoList = restTemplateService.getMemberLog(shop);

            // 상품 목록 조회
            List<ProductListResponse> productList = restTemplateService.getProductList(shop);

            RecommendProductUpdateRequest updateRequestBody = new RecommendProductUpdateRequest(productList, shop.getName(), "update", 0);
            restTemplateService.updateProductListWithRecommendForAi(updateRequestBody);

            for(MemberInfoRestResponse memberInfo: memberInfoList){
                Long memberId = memberInfo.getId();
                // 사용자별 구매기록 조회
                List<PurchaseLogRequest> purchaseLogList = restTemplateService.getPurchaseLogByMemberId(shop, memberId);
                purchaseLogList.sort(Comparator.comparing(PurchaseLogRequest::getPurchaseTime));
                // ai에 요청하여 가장 최근 구매한 제품의 상품 추천 받기
                RecommendAiRequest requestBody = new RecommendAiRequest(shop.getName(), purchaseLogList.getLast().getProduct().name(), productRecommend.getIsReview(), productRecommend.getIsStarCount(), productRecommend.getIsPostDate(), productRecommend.getK());
                List<RecommendAiResponse> responseBody = restTemplateService.getRecommendForAi(requestBody);
                // 쇼핑몰 서버로 추전 체품 post 요청
                for(RecommendAiResponse request : responseBody){
                    ProductRecommendRequest productRecommendRequest = new ProductRecommendRequest(memberInfo.getEmail(), request.productName());
                    restTemplateService.postReceommendProduct(shop, productRecommendRequest);
                }
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateCouponLog(){
        List<Shop> shopList = shopRepository.findAll();

        for(Shop shop: shopList){
            Long shopId = shop.getId();
            //사용자 조회
            List<MemberInfoRestResponse> memberInfoList = restTemplateService.getMemberLog(shop);

            for(MemberInfoRestResponse memberInfo: memberInfoList){
                Long memberId = memberInfo.getId();
                //사용자별 구매기록 조회
                List<PurchaseLogRequest> purchaseLogList = restTemplateService.getPurchaseLogByMemberId(shop, memberId);
                //ai에 요청하여 쿠폰 발급 여부 조회
                Optional<CouponRestResponse> optional = getCouponForAi(shopId, purchaseLogList);
                if (optional.isEmpty()) continue;
                CouponRestResponse couponRestResponse = optional.get();
                //CouponLog에 저장, 쿠폰 전송
                addCouponLog(shop, memberId, couponRestResponse, memberInfo);

            }
        }
    }

    @Transactional
    public void addCouponLog(Shop shop, Long memberId, CouponRestResponse couponRestResponse, MemberInfoRestResponse memberInfo){
        Long shopId = shop.getId();
        Outflux outflux = outfluxRepository.findByShopId(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰에 해당하는 이탈방지 파라미터 값을 찾을 수 없습니다."));

        List<CouponLog> couponLogList = couponLogRepository.findByShopIdAndMemberIdOrderByPostDateDesc(shopId, memberId);
        if(couponLogList.isEmpty() || couponLogList.getFirst().getPostDate().isBefore(LocalDate.now().minusMonths(outflux.getReIssue()))){
            boolean isOutflux = couponRestResponse.category() == null;
            CouponLog couponLog = new CouponLog(memberId, memberInfo.getGender(), memberInfo.getAge(), LocalDate.now(), isOutflux , shop);
            couponLogRepository.save(couponLog);
            AddCouponRequest addCouponRequest = new AddCouponRequest(memberId, couponRestResponse.category(), couponRestResponse.code(), couponRestResponse.discount());
            restTemplateService.postCoupon(shop, addCouponRequest);
            //smtp 메일 전송
        }
    }

    @Transactional(readOnly = true)
    public Optional<CouponRestResponse> getCouponForAi(Long shopId, List<PurchaseLogRequest> purchaseLogList){
        Outflux outflux = outfluxRepository.findByShopId(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰에 해당하는 이탈방지 파라미터 값을 찾을 수 없습니다."));
        OutfluxAiRquest requestBody = new OutfluxAiRquest(purchaseLogList, outflux.getLastPurchase(), outflux.getLastRefund(), outflux.getRefundPercent(), outflux.getPurchaseWithCategory(), outflux.getPurchaseNumber());

        return Optional.ofNullable(restTemplateService.getCouponForAi(requestBody));
    }
}
