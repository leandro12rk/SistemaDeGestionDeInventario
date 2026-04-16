package io.github.leandro12rk.inventory.model.goodsReceipts;


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

public class GoodsReceiptsModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer purchase_order_id;
    private java.time.LocalDateTime received_date;
    private String received_by;
}
