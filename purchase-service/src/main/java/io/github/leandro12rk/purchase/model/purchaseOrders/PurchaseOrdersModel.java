package io.github.leandro12rk.purchase.model.purchaseOrders;


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
@Table(name = "purchase_orders") // Nombre de la tabla en Postgres
public class PurchaseOrdersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long supplier_id;
    private java.time.LocalDateTime order_date;
    private String status ;//('PENDING', 'APPROVED', 'RECEIVED', 'CANCELLED')
    private Double total_amount;
}
