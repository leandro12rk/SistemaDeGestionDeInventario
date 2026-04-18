package io.github.leandro12rk.inventory.controller.inventory;


import io.github.leandro12rk.inventory.model.inventory.InventoryModel;
import io.github.leandro12rk.inventory.service.inventory.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/inventory")
public class InventoryController {

    InventoryService  inventoryService;
    public InventoryController(InventoryService inventoryService) {
this.inventoryService = inventoryService;
    }

    @DeleteMapping
    public ResponseEntity<?>deleteInventoryById(@RequestBody List<Long> listInventoryId) {
        inventoryService.deleteInventoryById(listInventoryId);
        return ResponseEntity.noContent().build();
    }
}
