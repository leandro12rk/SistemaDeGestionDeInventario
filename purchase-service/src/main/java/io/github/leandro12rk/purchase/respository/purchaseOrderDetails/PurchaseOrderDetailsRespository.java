package io.github.leandro12rk.purchase.respository.purchaseOrderDetails;
import io.github.leandro12rk.purchase.model.purchaseOrderDetails.PurchaseOrderDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderDetailsRespository extends JpaRepository<PurchaseOrderDetailsModel, Long>  {
}
