package io.github.leandro12rk.inventory.controller.stockMovements;


import io.github.leandro12rk.inventory.model.stockMovements.StockMovementsModel;
import io.github.leandro12rk.inventory.respository.stockMovements.StockMovementsRespository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stockMovements")
public class StockMovementsController {

    StockMovementsRespository stockMovementsRespository;

    public StockMovementsController(StockMovementsRespository stockMovementsRespository) {
        this.stockMovementsRespository = stockMovementsRespository;
    }
    @GetMapping
    public List<StockMovementsModel> findAllStockMovements() {
        return stockMovementsRespository.findAll();
    }
    @GetMapping("/{stockMovementsId}")
    public StockMovementsModel findStockMovements(@PathVariable Long stockMovementsId) {
        return stockMovementsRespository.findById(stockMovementsId).orElse(null);
    }
    @PostMapping
    public StockMovementsModel createStockMovements (@RequestBody StockMovementsModel stockMovements){
        return stockMovementsRespository.save(stockMovements);
    }

    @PutMapping
    public StockMovementsModel updateStockMovements (@RequestBody StockMovementsModel stockMovements){
        return stockMovementsRespository.save(stockMovements);
    }
    @DeleteMapping("/{stockMovementsId}")
    public void deleteStockMovements (@PathVariable Long stockMovementsId){
        stockMovementsRespository.deleteById(stockMovementsId);
    }
}
