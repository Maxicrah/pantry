package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.ICategoryDAO;
import com.maxi.pantrypos.dto.CategoryDTO;
import com.maxi.pantrypos.model.Category;
import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.service.ICategoryService;
import com.maxi.pantrypos.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryDAO categoryDAO;
    private final IProductService productService;
    public CategoryService(ICategoryDAO categoryDAO, IProductService productService) {
        this.categoryDAO = categoryDAO;
        this.productService = productService;
    }


    @Override
    public Category editCategory(Category category, Long id) {
        Category existingCategory = this.findCategoryById(id);
        if (existingCategory == null) {
            throw new RuntimeException("Category not found");
        }

        if (category.getCategoryName() != null) {
            existingCategory.setCategoryName(category.getCategoryName());
        }
        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            existingCategory.setProducts(category.getProducts());
        }

        return this.categoryDAO.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = this.findCategoryById(id);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new RuntimeException("Cannot delete a category with associated products.");
        }
        this.categoryDAO.delete(category);
    }

    @Override
    public Product addProductToCategory(Long id, String productName) {
       Category category = this.findCategoryById(id);
       Product product = this.productService.findProductByName(productName);
       if (category.getProducts().contains(product)) {
            throw new RuntimeException("Product already exists in this category.");
       }
       if(product == null){
           throw new RuntimeException("Product not found");
       }
       category.getProducts().add(product);
       this.categoryDAO.save(category);
       return product;
    }

    @Override
    public Product removeProductAtCategory(Long id, String productName) {
        Category category = this.findCategoryById(id);
        Product product = this.productService.findProductByName(productName);
        if(!category.getProducts().contains(product)) {
            throw new RuntimeException("Product not found");
        }
        category.getProducts().remove(product);
        this.categoryDAO.save(category);
        return product;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = this.categoryDAO.findAll();
        if(categories.isEmpty()) {
            throw new RuntimeException("Categories not found");
        }
        return categories;
    }

    @Override
    public Category saveCategory(CategoryDTO category) {
        Category category1 = new Category();
        category1.setCategoryName(category.getCategoryName());
        return this.categoryDAO.save(category1);
    }

    @Override
    public Category findCategoryById(Long id) {
        return this.categoryDAO.findById(id).orElse(null);
    }
}
