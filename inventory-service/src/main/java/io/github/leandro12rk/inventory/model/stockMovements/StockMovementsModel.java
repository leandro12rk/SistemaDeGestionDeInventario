package io.github.leandro12rk.inventory.model.stockMovements;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "goods_receipts") // Nombre de la tabla en Postgres

public class StockMovementsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int productId;
    private String type;
    private Double amount;
    private String reason;
    private java.time.LocalDateTime received_date;
}
