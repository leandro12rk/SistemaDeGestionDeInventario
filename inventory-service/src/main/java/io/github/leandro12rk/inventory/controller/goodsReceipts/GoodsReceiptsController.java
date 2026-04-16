package io.github.leandro12rk.inventory.controller.goodsReceipts;

import io.github.leandro12rk.inventory.model.goodsReceipts.GoodsReceiptsModel;
import io.github.leandro12rk.inventory.service.goodsReceipts.GoodsReceiptsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodReceipts")
public class GoodsReceiptsController {

    GoodsReceiptsService goodsReceiptsService;

    GoodsReceiptsController(GoodsReceiptsService goodsReceiptsService) {
        this.goodsReceiptsService = goodsReceiptsService;
    }

    @GetMapping
    public ResponseEntity <List<GoodsReceiptsModel>> getAllGoodReceipts() {
       return ResponseEntity.ok(goodsReceiptsService.getAllGoodReceipts());
    }

    @GetMapping("/{goodReceiptsId}")
    public ResponseEntity<GoodsReceiptsModel> getGoodsReceiptsById(@PathVariable Long goodReceiptsId) {
        return ResponseEntity.ok(goodsReceiptsService.getGoodsReceiptsById(goodReceiptsId));
    }

    @PostMapping
    public ResponseEntity<GoodsReceiptsModel> createNewGoodsReceipts(@RequestBody GoodsReceiptsModel goodsReceipts) {
        GoodsReceiptsModel created = goodsReceiptsService.createNewGoodsReceipts(goodsReceipts);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{goodsReceiptsId}")
    public ResponseEntity<GoodsReceiptsModel> updateGoodsReceiptsById(@PathVariable Long goodsReceiptsId, @RequestBody GoodsReceiptsModel goodsReceipts) {
        return ResponseEntity.ok(goodsReceiptsService.updateGoodsReceiptsById(goodsReceiptsId, goodsReceipts));
    }


}
