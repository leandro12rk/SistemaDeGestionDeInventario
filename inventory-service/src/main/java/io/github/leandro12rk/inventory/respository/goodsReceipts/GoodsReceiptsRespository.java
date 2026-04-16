package io.github.leandro12rk.inventory.respository.goodsReceipts;

import io.github.leandro12rk.inventory.model.goodsReceipts.GoodsReceiptsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsReceiptsRespository extends  JpaRepository<GoodsReceiptsModel, Long >{
}
