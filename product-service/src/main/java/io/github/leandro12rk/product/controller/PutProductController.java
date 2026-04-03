package io.github.leandro12rk.product.controller;

import io.github.leandro12rk.product.model.Product;
import io.github.leandro12rk.product.repository.ProductRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PutProductController {

    private final ProductRepository productRepository;


    public PutProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product productDetails) {
        // 1. Buscamos si el producto existe antes de intentar actualizar
        return productRepository.findById(productId).map(product -> {
            // 2. Actualizamos los campos con los nuevos datos que vienen en el Body
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setCategory(productDetails.getCategory());
            product.setPrice(productDetails.getPrice());

            // 3. Guardamos los cambios (esto disparará un UPDATE en SQL)
            return productRepository.save(product);
        }).orElse(null); // Aquí podrías lanzar una excepción personalizada después
    }

}
