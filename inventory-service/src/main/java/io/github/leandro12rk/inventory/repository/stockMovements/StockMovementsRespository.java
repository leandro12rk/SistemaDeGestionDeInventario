package io.github.leandro12rk.inventory.repository.stockMovements;


import io.github.leandro12rk.inventory.model.stockMovements.StockMovementsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementsRespository extends JpaRepository<StockMovementsModel, Long> {}
