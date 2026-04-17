package io.github.leandro12rk.inventory.controller.inventory;


import io.github.leandro12rk.inventory.client.product.ProductClient;
import io.github.leandro12rk.inventory.dto.ProductDTO;
import io.github.leandro12rk.inventory.model.inventory.InventoryModel;
import io.github.leandro12rk.inventory.respository.inventory.InventoryRespository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/inventory")
public class InventoryController {

    private final ProductClient productClient;
    InventoryRespository inventoryRespository;

    public InventoryController(InventoryRespository inventoryRespository, ProductClient productClient) {
        this.inventoryRespository = inventoryRespository;
        this.productClient = productClient;
    }


    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllInventory() {

        List<InventoryModel> inventoryList = inventoryRespository.findAll();

        List<Map<String, Object>> responseList = inventoryList.stream().map(item -> {
            Map<String, Object> response = new HashMap<>();
            try {
                ProductDTO product = productClient.getProductName(item.getProductId());
                response.put("productName", product.getName());
            } catch (Exception e) {
                response.put("productName", "Nombre no disponible");
            }

            response.put("inventoryId", item.getId());
            response.put("productId", item.getProductId());
            response.put("inventoryLastUpdate", item.getLast_updated());
            response.put("stock", item.getQuantity());

            return response;
        }).toList();

        return ResponseEntity.ok(responseList);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<?> getInventoryData(@PathVariable Long productId) {

        var inventory = inventoryRespository.findById(productId);

        ProductDTO product = productClient.getProductName(productId);

        Map<String, Object> response = new HashMap<>();
        response.put("inventoryId", inventory.get().getId());
        response.put("productId", inventory.get().getProductId());
        response.put("productName", product.getName());
        response.put("inventoryLastUpdate", inventory.get().getLast_updated());
        response.put("stock", inventory.get().getQuantity());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public InventoryModel addInventory(@RequestBody InventoryModel inventoryModel) {
        return inventoryRespository.save(inventoryModel);
    }

    @PutMapping
    public InventoryModel updateInventory(@RequestBody InventoryModel inventoryModel) {
        return inventoryRespository.save(inventoryModel);
    }

    @DeleteMapping
    public void deleteInventory(@RequestBody InventoryModel inventoryModel) {
        inventoryRespository.delete(inventoryModel);
    }
}
