package knu.project.crm.sale.service;

import knu.project.crm.shop.entity.Shop;
import knu.project.crm.common.exception.NotFoundException;
import knu.project.crm.shop.repository.ShopRepository;
import knu.project.crm.common.service.RestTemplateService;
import knu.project.crm.outflux.dto.PurchaseLogRequest;
import knu.project.crm.sale.dto.RecommendResultResponse;
import knu.project.crm.sale.dto.SaleForRecommendResponse;
import knu.project.crm.sale.dto.SaleResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleService {
    private final ShopRepository shopRepository;
    private final RestTemplateService restTemplateService;

    public SaleService(ShopRepository shopRepository, RestTemplateService restTemplateService){
        this.shopRepository = shopRepository;
        this.restTemplateService = restTemplateService;
    }

    @Transactional(readOnly = true)
    public List<SaleResponse> getSale(Long shopId){
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰을 찾을 수 없습니다."));

        List<PurchaseLogRequest> responseEntity = restTemplateService.getPurchaseLog(shop);

        return responseEntity.stream().map(PurchaseLogRequest::mapToResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SaleForRecommendResponse> getPurchaseLogForSale(Long shopId){
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰을 찾을 수 없습니다."));

        List<PurchaseLogRequest> responseEntity = restTemplateService.getPurchaseLog(shop);

        return responseEntity.stream().map(PurchaseLogRequest::mapToRecommendResponse).toList();
    }

    @Transactional(readOnly = true)
    public RecommendResultResponse getRecommend(Long shopId){
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰을 찾을 수 없습니다."));

        return restTemplateService.getRecommendProduct(shop);
    }
}
