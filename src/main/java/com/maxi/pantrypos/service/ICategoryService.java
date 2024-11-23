package com.maxi.pantrypos.service;

import com.maxi.pantrypos.model.Category;
import com.maxi.pantrypos.model.Product;

import java.util.List;

public interface ICategoryService {
    public Category editCategory(Category category, Long id);
    public void deleteCategory(Long id);
    public Product addProductToCategory(Long id, String productName);
    public Product removeProductAtCategory(Long id, String productName);
    public List<Category> getAllCategories();
    public Category saveCategory(Category category);
    public Category findCategoryById(Long id);

}
