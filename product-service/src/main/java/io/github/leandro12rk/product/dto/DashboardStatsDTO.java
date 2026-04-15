package io.github.leandro12rk.product.dto;

import io.github.leandro12rk.product.dto.category.CategoryStatsDTO;
import io.github.leandro12rk.product.dto.product.ProductStatsDTO;
import io.github.leandro12rk.product.dto.supplier.SupplierStatsDTO;
import jakarta.persistence.Cacheable;
import lombok.Data;

import java.util.List;

@Data

public class DashboardStatsDTO {
    ProductStatsDTO productsStats;
    List<CategoryStatsDTO> productsByCategory;
    List<SupplierStatsDTO> productBySupplier;
}
