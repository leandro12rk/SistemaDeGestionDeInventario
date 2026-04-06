package io.github.leandro12rk.product.controller.category;

import io.github.leandro12rk.product.model.category.CategoryModel;
import io.github.leandro12rk.product.repository.category.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")

public class CategoryController {
    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @DeleteMapping("/{categoryId}")
    public void deleteProduct(@PathVariable("categoryId") Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
    @GetMapping
    public List<CategoryModel> getAllCategory(){
        return  categoryRepository.findAll();
    }

    @GetMapping("/{categoryId}")
    public CategoryModel getCategoryById(@PathVariable("categoryId") Long categoryId){
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @PostMapping
    public CategoryModel CreateCategory(@RequestBody CategoryModel categoryModel) {
        return categoryRepository.save(categoryModel);
    }

//    @PutMapping
//    public Category UpdateCategory(@RequestBody Category category) {
//        return categoryRepository.save(category);
//    }

}
