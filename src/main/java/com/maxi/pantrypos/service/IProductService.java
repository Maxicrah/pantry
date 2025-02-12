package com.maxi.pantrypos.service;

import com.maxi.pantrypos.dto.ProductDTO;
import com.maxi.pantrypos.model.Product;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface IProductService {

    //create product
    public Product save(ProductDTO product, MultipartFile image) throws IOException;
    //delete product
    public void deleteProduct(Long id);
    //update product
    public Product updateProduct(Long idOriginalProduct,
                                 ProductDTO product,
                                 MultipartFile image) throws IOException;
    //get product by id
    public Product getProduct(Long id);
    //get products
    public List<Product> getAllProducts();
    //find product by name
    public Product findProductByName(String name);
    //validate offer
    public Boolean validateOffer(Long id);
    //validate weigh-able product
    public Boolean validateWeighAbleProduct(Long id);
    //get best-selling product
    public Product getBestSellingProduct();
    //get least sold product
    public Product getLeastSoldProduct();
    //update price product
    public Product updatePrice(Long id, Double price);
    //days to expiration product
    public Long getDaysToExpiration(Long id);
    //update stock product
    public void updateStock(Long id, Long stock);

    public List<Product> findProductsByIds(List<Long> productIds);

}
