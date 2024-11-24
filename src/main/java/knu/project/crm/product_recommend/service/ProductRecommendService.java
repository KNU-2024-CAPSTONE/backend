package knu.project.crm.product_recommend.service;

import knu.project.crm.common.exception.NotFoundException;
import knu.project.crm.product_recommend.dto.ProductRecommendRequest;
import knu.project.crm.product_recommend.dto.ProductRecommendResponse;
import knu.project.crm.product_recommend.entity.ProductRecommend;
import knu.project.crm.product_recommend.repository.ProductRecommendRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductRecommendService {
    private final ProductRecommendRepository productRecommendRepository;

    public ProductRecommendService(ProductRecommendRepository productRecommendRepository){
        this.productRecommendRepository = productRecommendRepository;
    }

    @Transactional(readOnly = true)
    public ProductRecommendResponse getParmeters(Long shopId){
        ProductRecommend productRecommend = productRecommendRepository.findByShopId(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰에 해당하는 상품추천 파라미터 값을 찾을 수 없습니다."));

        return productRecommend.mapToResponse();
    }

    @Transactional
    public void updateParameters(Long shopId, ProductRecommendRequest productRecommendRequest){
        ProductRecommend productRecommend = productRecommendRepository.findByShopId(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰에 해당하는 상품추천 파라미터 값을 찾을 수 없습니다."));

        productRecommend.updateParameters(productRecommendRequest);
    }
}
