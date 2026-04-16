package io.github.leandro12rk.product.controller.category;

import io.github.leandro12rk.product.model.category.CategoryModel;
import io.github.leandro12rk.product.model.supplier.SupplierModel;
import io.github.leandro12rk.product.repository.category.CategoryRepository;
import io.github.leandro12rk.product.service.category.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Category Module Gestion")

public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{categoryId}")
    public CategoryModel getCategoryById(@PathVariable("categoryId") Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    // Delete Product By ID
    public ResponseEntity<Void> deleteCategoryByID(@PathVariable Long productId) {
        categoryService.deleteCategoryById(productId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CategoryModel> createNewCategory(@RequestBody CategoryModel category) {
        CategoryModel created = categoryService.createNewCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryModel> updateCategoryById(@PathVariable Long categoryId, @RequestBody CategoryModel category) {
        return ResponseEntity.ok(categoryService.updateCategoryById(categoryId, category));
    }


}
