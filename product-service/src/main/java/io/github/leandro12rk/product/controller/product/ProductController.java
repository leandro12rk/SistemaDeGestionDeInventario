package io.github.leandro12rk.product.controller.product;

import io.github.leandro12rk.product.model.product.ProductModel;
import io.github.leandro12rk.product.projection.product.ProductGetProjection;
import io.github.leandro12rk.product.projection.product.ProductNameProjection;
import io.github.leandro12rk.product.repository.product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;


    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // 1. Obtener todos los productos
    @GetMapping
    public List<ProductGetProjection> getAllProducts() {
        return productRepository.findAllProjectedBy();
    }

    // 2. Obtener un producto por ID
    @GetMapping("/{productId}")
    public ResponseEntity<ProductGetProjection> getProductById(@PathVariable Long productId) {
        return productRepository.findProjectedById(productId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{productId}/name")
    public ResponseEntity<ProductNameProjection> getProductName(@PathVariable Long productId) {
        return productRepository.findNameProjectById(productId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return "Producto con ID " + productId + " eliminado correctamente.";
        }).orElse("Error: No se encontró el producto con ID " + productId);
    }

    @PostMapping
    public ProductModel createProduct(@RequestBody ProductModel product) {
        return productRepository.save(product);
    }

    @PutMapping("/{productId}")
    public ProductModel updateProduct(@PathVariable Long productId, @RequestBody ProductModel productDetails) {
        // 1. Buscamos si el producto existe antes de intentar actualizar
        return productRepository.findById(productId).map(product -> {
            product.setId(productId);
            // 2. Actualizamos los campos con los nuevos datos que vienen en el Body
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setSku(productDetails.getSku());
            product.setStatus(productDetails.isStatus());
            if (productDetails.getCategory() != null) {
                product.setCategory(productDetails.getCategory());
            }
            if (productDetails.getSupplier() != null) {
                product.setSupplier(productDetails.getSupplier());
            }
            // 3. Guardamos los cambios (esto disparará un UPDATE en SQL)
            return productRepository.save(product);
        }).orElse(null); // Aquí podrías lanzar una excepción personalizada después
    }
}
