package knu.project.crm.influx.service;

import knu.project.crm.shop.entity.Shop;
import knu.project.crm.shop.repository.ShopRepository;
import knu.project.crm.common.service.RestTemplateService;
import knu.project.crm.influx.dto.InfluxResponse;
import knu.project.crm.influx.dto.MemberInfoRestResponse;
import knu.project.crm.common.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InfluxService {
    private final ShopRepository shopRepository;
    private final RestTemplateService restTemplateService;

    public InfluxService(ShopRepository shopRepository, RestTemplateService restTemplateService){
        this.shopRepository = shopRepository;
        this.restTemplateService = restTemplateService;
    }

    @Transactional(readOnly = true)
    public List<InfluxResponse> getInflux(Long shopId){
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("쇼핑몰을 찾을 수 없습니다."));

        List<MemberInfoRestResponse> responseEntity = restTemplateService.getMemberLog(shop);
        return responseEntity.stream().map(MemberInfoRestResponse::mapToResponse).toList();
    }
}
