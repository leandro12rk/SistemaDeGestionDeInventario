package io.github.leandro12rk.product.controller.category;

import io.github.leandro12rk.product.repository.ProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeleteCategoryController {
    ProductRepository productRepository;

    public DeleteCategoryController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @DeleteMapping("/{categoryId}")
    public void deleteProduct(@PathVariable("categoryId") Long categoryId) {
        productRepository.deleteById(categoryId);
    }
}
