package io.github.leandro12rk.product.controller;

import io.github.leandro12rk.product.model.Product;
import io.github.leandro12rk.product.repository.ProductRepository; // Ojo a la escritura de "repository"
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list")
public class GetProductController {

    private final ProductRepository productRepository;


    public GetProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}