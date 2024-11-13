package knu.project.crm.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.findAll();
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale savedSale = saleService.save(sale);
        return new ResponseEntity<>(savedSale, HttpStatus.CREATED);
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable Integer saleId) {
        if (saleService.findById(saleId).isPresent()) {
            saleService.deleteById(saleId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 구매 로그 조회
    @GetMapping("/logs")
    public String getPurchaseLog() {
        return saleService.getPurchaseLog();
    }

    // 특정 회원의 구매 로그 조회
    @GetMapping("/logs/{memberId}")
    public String getPurchaseLogByMemberId(@PathVariable String memberId) {
        return saleService.getPurchaseLogByMemberId(memberId);
    }
}
