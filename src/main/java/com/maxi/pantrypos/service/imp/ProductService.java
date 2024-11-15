package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.IProductDAO;
import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.service.IProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductDAO productDAO;
    public ProductService(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product save(Product product) {
        if(product == null) {
            throw new RuntimeException("product is null");
        }
        return this.productDAO.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = this.getProduct(id);
        if(product == null) {
            throw new RuntimeException("product is null");
        }
        this.productDAO.delete(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product newProduct =this.getProduct(id);
        if(newProduct == null) {
            throw new RuntimeException("product is null");
        }
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setCost(product.getCost());
        newProduct.setEntryDate(product.getEntryDate());
        newProduct.setExpirationDate(product.getExpirationDate());
        newProduct.setImage(product.getImage());
        newProduct.setIsOnSale(product.getIsOnSale());
        newProduct.setUnitOfMeasure(product.getUnitOfMeasure());
        return this.productDAO.save(newProduct);
    }

    @Override
    public Product getProduct(Long id) {
        if(id > 999L){
            throw new RuntimeException("id greater than 999");
        }
        return this.productDAO.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productDAO.findAll();
    }

    @Override
    public Product findProductByName(String name) {

        Product product = this.productDAO.findByName(name);
        if(product == null) {
            throw new RuntimeException("product with name " + name + " not found");
        }
        return product;
    }

    @Override
    public Boolean validateOffer(Long id) {
        Product product = this.getProduct(id);
        return product.getIsOnSale();
    }

    @Override
    public Boolean validateWeighAbleProduct(Long id) {
        return null;
    }

    @Override
    public Product getBestSellingProduct() {

        return this.productDAO.findBestSellingProduct().orElseThrow(()
                -> new RuntimeException("no product found"));
    }

    @Override
    public Product getLeastSoldProduct() {
        return null;
    }

    @Override
    public Product updatePrice(Long id, Double price) {
        Product product = this.getProduct(id);
        if(product == null){
            throw new RuntimeException("product is null");
        }
        product.setPrice(price);
        return this.productDAO.save(product);
    }

    @Override
    public Long getDaysToExpiration(Long id) {
        Product product = this.getProduct(id);
        LocalDate expirationDate = product.getExpirationDate();
        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(today, expirationDate);
    }

    @Override
    public void updateStock(Long id, Long stock) {
        Product product = this.getProduct(id);
        product.setStock(stock);
        this.productDAO.save(product);
    }
}
