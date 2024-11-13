package knu.project.crm.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    public Optional<Shop> findById(Integer id) {
        return shopRepository.findById(id);
    }

    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    public void deleteById(Integer id) {
        shopRepository.deleteById(id);
    }
}
