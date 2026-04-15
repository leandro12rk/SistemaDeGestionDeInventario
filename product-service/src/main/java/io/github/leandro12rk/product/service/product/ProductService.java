package io.github.leandro12rk.product.service.product;


import io.github.leandro12rk.product.dto.DashboardStatsDTO;
import io.github.leandro12rk.product.dto.product.ProductFilterDTO;
import io.github.leandro12rk.product.exception.ResourceNotFoundException;
import io.github.leandro12rk.product.model.product.ProductModel;
import io.github.leandro12rk.product.projection.product.ProductGetProjection;
import io.github.leandro12rk.product.projection.product.ProductNameProjection;
import io.github.leandro12rk.product.repository.product.ProductRepository;

import jakarta.persistence.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //  Get All Products
    public List<ProductGetProjection> getAllProducts(ProductFilterDTO filter) {
        boolean actualStatus = (filter.getStatus() != null) ? filter.getStatus() : true;

        Specification<ProductModel> spec = Specification.unrestricted();

        // Filter Validations
        if (filter.getProductName() != null && !filter.getProductName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("name"), filter.getProductName()));
        }
        if (filter.getSku() != null && !filter.getSku().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("sku"), filter.getSku()));
        }
        if (filter.getCategoryName() != null && !filter.getCategoryName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("category").get("name"), filter.getCategoryName()));
        }
        if (filter.getSupplierName() != null && !filter.getSupplierName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("supplier").get("companyName"), filter.getSupplierName()));
        }

        // declaracion de status por defecto en true
        spec = spec.and((root, query, cb) ->
                cb.equal(root.get("status"), actualStatus));

        return productRepository.findBy(spec, q ->
                q.as(ProductGetProjection.class).all());
    }

    // Get Product Name By ID
    public ProductNameProjection getProductName(Long productId) {
        return productRepository.findNameProjectById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product Non Found: " + productId));
    }

    // Get Dashboard From Database
    public DashboardStatsDTO getDashboardData() {
        var global = productRepository.getGlobalStatistics();
        var byCategory = productRepository.getStatsByCategory();
        var bySupplier = productRepository.getStatsBySupplier();

        DashboardStatsDTO dto = new DashboardStatsDTO();
        dto.setProductsStats(global);
        dto.setProductsByCategory(byCategory);
        dto.setProductBySupplier(bySupplier);

        return dto;
    }

    // Delete Product By ID
    public void deleteProductById(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Could no Delete , Product not Found: " + productId);
        }
        productRepository.deleteById(productId);
    }

    // Create New Product
    public ProductModel createProduct(ProductModel product) {
        return productRepository.save(product);
    }

    // Update Product By ID
    public ProductModel updateProductById(Long productId, ProductModel details) {
        return productRepository.findById(productId).map(existingProduct -> {

            existingProduct.setName(details.getName());
            existingProduct.setDescription(details.getDescription());
            existingProduct.setPrice(details.getPrice());
            existingProduct.setSku(details.getSku());
            existingProduct.setStatus(details.isStatus());

            if (details.getCategory() != null) existingProduct.setCategory(details.getCategory());
            if (details.getSupplier() != null) existingProduct.setSupplier(details.getSupplier());

            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new ResourceNotFoundException("Could not be updated , Product dosen't exists with Id: " + productId));
    }
}
