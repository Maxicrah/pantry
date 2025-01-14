package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.IProductDAO;
import com.maxi.pantrypos.dto.ProductDTO;
import com.maxi.pantrypos.exception.product.ProductEntryDateException;
import com.maxi.pantrypos.exception.product.ProductNotFoundException;
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
    public Product save(ProductDTO product) {
        if(product.getUnitOfMeasure() == null || product.getIsOnSale() == null) {
            throw new ProductNotFoundException("product is incomplete", "verify content unit of measure or is on sale");
        }
        Product prod = new Product();
        prod.setName(product.getName());
        prod.setDescription(product.getDescription());
        prod.setPrice(product.getPrice());
        if(!this.validateExpirationDate(product.getExpirationDate())) {
            throw new ProductEntryDateException("expiration date invalid", "verify expiration date");
        }
        prod.setExpirationDate(product.getExpirationDate());
        prod.setEntryDate(LocalDate.now());
        prod.setStock(product.getStock());
        prod.setCodProduct(product.getCodProduct());
        prod.setImage(product.getImage());
        prod.setCost(product.getCost());
        prod.setIsOnSale(product.getIsOnSale());
        prod.setUnitOfMeasure(product.getUnitOfMeasure());
        prod.setCategory(null);
        prod.setSuppliers(null);
        prod.setOffers(null);
        return this.productDAO.save(prod);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = this.getProduct(id);
        if(product == null) {
            throw new ProductNotFoundException("product not found", "product no exist in database");
        }
        this.productDAO.delete(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product newProduct =this.getProduct(id);
        if(newProduct == null) {
            throw new ProductNotFoundException("product not found", "product no exist in database");
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

        Product product = this.productDAO.findById(id).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException("product not found", "product no exist in database");
        }
        if(product.getIdProduct() > 999){
            throw new ProductNotFoundException("product not found", "product id too large");
        }
        return product;
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

    @Override
    public List<Product> findProductsByIds(List<Long> productIds) {
        return productDAO.findAllById(productIds);
    }


    private Double calculateSuggestedPrice(Double price){
       Double suggestedPrice;
       suggestedPrice = price * 1.3;
       return suggestedPrice;
    }

    private boolean validateExpirationDate(LocalDate expirationDate) {
        LocalDate today = LocalDate.now();
        return expirationDate.isAfter(today);
    }

}
