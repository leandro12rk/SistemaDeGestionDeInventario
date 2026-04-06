package io.github.leandro12rk.purchase.model.purchaseOrderDetails;


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
@Table(name = "purchase_order_details") // Nombre de la tabla en Postgres
public class PurchaseOrderDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long purchase_order_id;
    private Long product_id;
    private Integer quantity_ordered;
    private Double unit_price;

}
