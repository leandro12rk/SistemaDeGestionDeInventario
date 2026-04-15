package io.github.leandro12rk.product.service.category;


import io.github.leandro12rk.product.exception.ResourceNotFoundException;
import io.github.leandro12rk.product.model.category.CategoryModel;
import io.github.leandro12rk.product.repository.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get all Category Data
    public List<CategoryModel> getAllCategory(){
        return  categoryRepository.findAll();
    }

    // Get Category By Id
    public CategoryModel getCategoryById( Long categoryId){
        return categoryRepository.findById(categoryId).orElse(null);
    }

    // Delete Product By ID
    public void deleteCategoryById(Long productId) {
        if (!categoryRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Could no Delete , Product not Found: " + productId);
        }
        categoryRepository.deleteById(productId);
    }
    // Create new Categoory
    public CategoryModel createNewCategory (CategoryModel category) {
        return categoryRepository.save(category);
    }

    // Update Category By Id
    public CategoryModel updateCategoryById(Long categoryId,CategoryModel categoryUpdate) {
        return categoryRepository.findById(categoryId).map(existingSupplier -> {
            existingSupplier.setName(categoryUpdate.getName());
            existingSupplier.setDescription(categoryUpdate.getDescription());
            existingSupplier.setStatus(categoryUpdate.isStatus());

            return categoryRepository.save(existingSupplier);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Could not be updated , Product dosen't exists with Id: " + categoryId));
    }


}
