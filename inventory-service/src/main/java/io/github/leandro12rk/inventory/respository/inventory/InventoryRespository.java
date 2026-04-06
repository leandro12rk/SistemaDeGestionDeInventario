package io.github.leandro12rk.inventory.respository.inventory;
import io.github.leandro12rk.inventory.model.inventory.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRespository extends JpaRepository<InventoryModel, Long >{
}
