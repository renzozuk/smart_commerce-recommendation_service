package dev.renzozukeram.recommendation.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "inventory")
public interface InventoryAccessService {

    @GetMapping("/product/string")
    String getAllProductsAsString();
}
