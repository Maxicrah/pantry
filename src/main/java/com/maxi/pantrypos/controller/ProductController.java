package com.maxi.pantrypos.controller;

import com.maxi.pantrypos.dto.ProductDTO;
import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<Map<String, Object>> createProduct(@Valid @RequestPart("product") ProductDTO product,
                                                             @RequestPart("image") MultipartFile image) throws IOException {
        Product savedProduct = this.productService.save(product, image);
        System.out.println(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "product created successfully",
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

    @PutMapping("/update/{idProduct}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable(name = "idProduct") Long idProduct,
                                                            @Valid @RequestPart ProductDTO product,
                                                             @RequestPart MultipartFile image) throws IOException {

        Product prod = this.productService.updateProduct(idProduct, product, image);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of(
                "message", "Product updated successfully",
                "product: ", prod
        ));
    }

    @PutMapping("/update-price/{idProduct}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "idProduct") Long idProduct,
                                           @RequestParam Double price){
       Product product = this.productService.updatePrice(idProduct, price);
        return ResponseEntity.ok(product);
    }



}
