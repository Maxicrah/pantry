package com.maxi.pantrypos.controller;

import com.maxi.pantrypos.dto.ProductDTO;
import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;


    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createProduct(@Valid @RequestBody ProductDTO product){
        Product savedProduct = this.productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Product created successfully",
                "product", savedProduct
        ));
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable(name= "idProduct") Long idProduct){
      Product product =  this.productService.getProduct(idProduct);
      return ResponseEntity.ok(Map.of(
                "product", product,
                "message", "Product retrieved successfully"
        ));    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = this.productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long id){
        this.productService.deleteProduct(id);
        return ResponseEntity.ok("Eliminado correctamente");
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Map<String, Object>> findProductByName(@PathVariable(name = "name") String name){
        Product product = this.productService.findProductByName(name);
        return ResponseEntity.ok(Map.of("product", product,
                "message", "Product retrieved successfully"
        ));
    }

    @PutMapping("/update-price/{idProduct}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "idProduct") Long idProduct,
                                           @RequestParam Double price){
       Product product = this.productService.updatePrice(idProduct, price);
        return ResponseEntity.ok(product);
    }



}
