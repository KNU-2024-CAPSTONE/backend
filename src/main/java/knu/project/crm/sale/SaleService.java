package knu.project.crm.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Optional<Sale> findById(Integer id) {
        return saleRepository.findById(id);
    }

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    public void deleteById(Integer id) {
        saleRepository.deleteById(id);
    }
    public String getPurchaseLog() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/database/purchase-log")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        System.out.println(responseEntity.getBody()); // 데이터가 제대로 받아지는지 확인

        return responseEntity.getBody();
    }

    public String getPurchaseLogByMemberId(String memberId) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/api/database/purchase-log/{memberId}")
                .encode()
                .build()
                .expand("memberId") // 복수의 값을 넣어야 할 경우 ,를 추가하여 구분
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }


}
