package io.github.leandro12rk.product.model;

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
    private String name;
    private String description;
    private String category;
    private Double price;
}