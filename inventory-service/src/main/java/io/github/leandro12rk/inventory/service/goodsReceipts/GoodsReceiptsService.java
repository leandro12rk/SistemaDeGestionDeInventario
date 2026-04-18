package io.github.leandro12rk.inventory.service.goodsReceipts;

import io.github.leandro12rk.inventory.model.goodsReceipts.GoodsReceiptsModel;
import io.github.leandro12rk.inventory.repository.goodsReceipts.GoodsReceiptsRespository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GoodsReceiptsService {

    GoodsReceiptsRespository goodReceiptsRespository;

    public GoodsReceiptsService(GoodsReceiptsRespository goodsReceiptsRespository) {
        this.goodReceiptsRespository = goodsReceiptsRespository;
    }


    public List<GoodsReceiptsModel> getAllGoodReceipts(){
        return goodReceiptsRespository.findAll();
    }

    public GoodsReceiptsModel getGoodsReceiptsById(Long goodReceiptsId){
        return goodReceiptsRespository.findById(goodReceiptsId).orElse(null);
    }


    public GoodsReceiptsModel createNewGoodsReceipts(GoodsReceiptsModel goodsReceiptsModel){
        return goodReceiptsRespository.save(goodsReceiptsModel);
    }

    public GoodsReceiptsModel updateGoodsReceiptsById(Long goodReceiptsId, GoodsReceiptsModel goodsReceiptsModel){
        return goodReceiptsRespository.save(goodsReceiptsModel);
    }
}
