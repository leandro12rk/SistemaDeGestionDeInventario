package io.github.leandro12rk.inventory.controller.goodsReceipts;

import io.github.leandro12rk.inventory.model.goodsReceipts.GoodReceiptsModel;
import io.github.leandro12rk.inventory.respository.goodsReceipts.GoodReceiptsRespository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodReceipts")
public class GoodReceiptsController {

    GoodReceiptsRespository goodReceiptsRespository;

    public GoodReceiptsController(GoodReceiptsRespository goodReceiptsRespository) {
        this.goodReceiptsRespository = goodReceiptsRespository;
    }

    @GetMapping
    public List<GoodReceiptsModel> findAll(){
        return goodReceiptsRespository.findAll();
    }

    @GetMapping("/{goodReceiptsId}")
    public GoodReceiptsModel findById(@PathVariable Long goodReceiptsId){
        return goodReceiptsRespository.findById(goodReceiptsId).orElse(null);
    }
    @PostMapping
    public GoodReceiptsModel crear(@RequestBody GoodReceiptsModel goodReceiptsModel){
        return goodReceiptsRespository.save(goodReceiptsModel);
    }
}
