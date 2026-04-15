package io.github.leandro12rk.product.repository.product;

import io.github.leandro12rk.product.dto.category.CategoryStatsDTO;
import io.github.leandro12rk.product.dto.product.ProductStatsDTO;
import io.github.leandro12rk.product.dto.supplier.SupplierStatsDTO;
import io.github.leandro12rk.product.model.product.ProductModel;
import io.github.leandro12rk.product.projection.product.ProductGetProjection;
import io.github.leandro12rk.product.projection.product.ProductNameProjection;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> , JpaSpecificationExecutor<ProductModel> {
    Optional<ProductGetProjection> findProjectedById(Long id);
    Optional<ProductNameProjection> findNameProjectById(Long id);


    // 1. Total de productos y suma de precios
    @Query("SELECT new io.github.leandro12rk.product.dto.product.ProductStatsDTO(SUM(p.price), COUNT(p)) " +
            "FROM ProductModel p")
    ProductStatsDTO getGlobalStatistics();

    // 2. Conteo por categoría
    @Query("SELECT new io.github.leandro12rk.product.dto.category.CategoryStatsDTO(c.name, COUNT(p)) " +
            "FROM ProductModel p JOIN p.category c " +
            "GROUP BY c.id, c.name")
    List<CategoryStatsDTO> getStatsByCategory();

    // 3. Conteo por proveedor
    @Query("SELECT new io.github.leandro12rk.product.dto.supplier.SupplierStatsDTO(s.companyName, COUNT(p)) " +
            "FROM ProductModel p JOIN p.supplier s " +
            "GROUP BY s.id, s.companyName")
    List<SupplierStatsDTO> getStatsBySupplier();
}

