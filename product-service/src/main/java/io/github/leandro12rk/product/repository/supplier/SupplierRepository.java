package io.github.leandro12rk.product.repository.supplier;


import io.github.leandro12rk.product.model.supplier.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierModel, Long> {
}
