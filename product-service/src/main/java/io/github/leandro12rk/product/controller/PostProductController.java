package io.github.leandro12rk.product.controller;


import io.github.leandro12rk.product.model.Product;
import io.github.leandro12rk.product.repository.ProductRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostProductController {

    private final ProductRepository productRepository;


    public PostProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/") // La ruta final será /api/v01/producto/
    public Product createProduct(@RequestBody Product product) {
        // El método .save() recibe el objeto, lo inserta en Postgres
        // y te devuelve el objeto ya con el ID generado.
        return productRepository.save(product);
    }


}
