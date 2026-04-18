package io.github.leandro12rk.inventory.repository.inventory;
import io.github.leandro12rk.inventory.model.inventory.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<InventoryModel, Long >{
}
