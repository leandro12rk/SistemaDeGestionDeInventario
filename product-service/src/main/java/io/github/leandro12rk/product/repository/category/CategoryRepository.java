package io.github.leandro12rk.product.repository.category;


import io.github.leandro12rk.product.model.category.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long>  {
}
