package io.github.leandro12rk.inventory.client.product;

import io.github.leandro12rk.inventory.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/product/{productId}/name")
    ProductDTO getProductName(@PathVariable("productId") Long productId);
}