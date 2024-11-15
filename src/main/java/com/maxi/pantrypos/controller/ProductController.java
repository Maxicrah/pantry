package com.maxi.pantrypos.controller;

import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;


    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        this.productService.save(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<?> getProduct(@PathVariable(name= "idProduct") Long idProduct){
      Product product =  this.productService.getProduct(idProduct);
        return ResponseEntity.ok(product);
    }

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
    public ResponseEntity<?> findProductByName(@PathVariable(name = "name") String name){
        Product product = this.productService.findProductByName(name);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update-price/{idProduct}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "idProduct") Long idProduct,
                                           @RequestParam Double price){
       Product product = this.productService.updatePrice(idProduct, price);
        return ResponseEntity.ok(product);
    }



}
