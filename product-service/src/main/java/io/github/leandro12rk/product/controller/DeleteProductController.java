package io.github.leandro12rk.product.controller;


import io.github.leandro12rk.product.repository.ProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteProductController {
    private final ProductRepository productRepository;


    public DeleteProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return "Producto con ID " + id + " eliminado correctamente.";
                })
                .orElse("Error: No se encontró el producto con ID " + id);
    }
}
