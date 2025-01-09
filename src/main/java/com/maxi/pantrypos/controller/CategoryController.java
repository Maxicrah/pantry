package com.maxi.pantrypos.controller;

import com.maxi.pantrypos.dto.CategoryDTO;
import com.maxi.pantrypos.model.Category;
import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;


    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>>  createCategory( @RequestBody @Valid CategoryDTO category){
        Category categorySaved = this.categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "created",
                "category", categorySaved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCategory(@PathVariable(name="id")Long id){
        Category category = this.categoryService.findCategoryById(id);
        if(category == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "category not found"
            ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "category", category
        ));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable(name="id")Long id){
        Category category = this.categoryService.findCategoryById(id);
        if(category == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "category not found"
            ));
        }
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of(
                "message", "deleted"
        ));
    }

    @PutMapping("/add-product/{id}/{productName}")
    public ResponseEntity<Map<String, Object>> addProduct(@PathVariable(name="id")Long id,
                                                          @PathVariable(name="productName")String productName){

        Product product = this.categoryService.addProductToCategory(id, productName);
        return ResponseEntity.ok(Map.of(
                "message", "Product added successfully",
                "product", product.getName()
        ));
    }

    @PutMapping("/remove-product/{id}/{productName}")
    public ResponseEntity<Map<String, Object>> removeProduct(@PathVariable(name="id")Long id,
                                                             @PathVariable(name="productName") String productName){

      Product product = this.categoryService.removeProductAtCategory(id, productName);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of(
              "message","Product deleted successfully",
              "product", product.getName()
      ));
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, List<Category>>> getAllCategory(){
        List<Category> categories = this.categoryService.getAllCategories();
        return ResponseEntity.ok(Map.of(
                "products", categories
        ));
    }

}
