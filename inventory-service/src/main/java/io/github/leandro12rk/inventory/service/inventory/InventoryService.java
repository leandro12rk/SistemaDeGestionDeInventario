package io.github.leandro12rk.inventory.service.inventory;


import io.github.leandro12rk.inventory.client.product.ProductClient;
import io.github.leandro12rk.inventory.dto.ProductDTO;
import io.github.leandro12rk.inventory.model.inventory.InventoryModel;
import io.github.leandro12rk.inventory.repository.inventory.InventoryRepository;
import io.github.leandro12rk.product.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {
    private final ProductClient productClient;

    InventoryRepository inventoryRepository;



    public InventoryService(InventoryRepository inventoryRepository, ProductClient productClient) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = productClient;
    }


    public InventoryModel updateInventoryById(Long inventoryId,InventoryModel inventoryUpdate) {
        return inventoryRepository.findById(inventoryId).map(existingInventory -> {

            existingInventory.setQuantity(inventoryUpdate.getQuantity());

            return inventoryRepository.save(existingInventory);
        }).orElseThrow(() -> new ResourceNotFoundException("Could not be updated , Product dosen't exists with Id: " + inventoryId));
    }

    // Delete Product By ID
    public void deleteInventoryById(List<Long>  listInventoryId) {
        for (Long inventoryId : listInventoryId) {
            if (!inventoryRepository.existsById(inventoryId)) {
                throw new ResourceNotFoundException("Could no Delete , Inventory not Found: " + inventoryId);
            }
            inventoryRepository.deleteById(inventoryId);
        }
    }
}
