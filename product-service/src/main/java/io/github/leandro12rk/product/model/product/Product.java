package io.github.leandro12rk.product.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.leandro12rk.product.model.supplier.Supplier;
import io.github.leandro12rk.product.model.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor // Esto reemplaza al constructor vacío manual "public Product() {}"
@AllArgsConstructor
@Entity
@Table(name = "products") // Nombre de la tabla en Postgres
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id") // Apunta al id de categories
    @JsonProperty("category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id") // Apunta al id de suppliers
    private Supplier supplier;

    private boolean status;
    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;


}