package io.github.leandro12rk.inventory.respository.goodsReceipts;

import io.github.leandro12rk.inventory.model.goodsReceipts.GoodReceiptsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodReceiptsRespository extends  JpaRepository<GoodReceiptsModel, Long >{
}
