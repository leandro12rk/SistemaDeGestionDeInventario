package io.github.leandro12rk.purchase.respository.purchaseOrders;

import io.github.leandro12rk.purchase.model.purchaseOrders.PurchaseOrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseOrdersRespository extends JpaRepository<PurchaseOrdersModel, Long> {
}
