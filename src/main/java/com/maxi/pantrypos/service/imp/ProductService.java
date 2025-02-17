package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.IProductDAO;
import com.maxi.pantrypos.dto.ProductDTO;
import com.maxi.pantrypos.exception.product.ProductCodeException;
import com.maxi.pantrypos.exception.product.ProductEntryDateException;
import com.maxi.pantrypos.exception.product.ProductNotFoundException;
import com.maxi.pantrypos.model.File;
import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.response.ResponseProduct;
import com.maxi.pantrypos.service.IFileService;
import com.maxi.pantrypos.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService{

    private final IProductDAO productDAO;
    public ProductService(IProductDAO productDAO) {
        this.productDAO = productDAO;

    }

    @Override
    public Product save(ProductDTO product, MultipartFile image) throws IOException {
        if(product.getUnitOfMeasure() == null || product.getIsOnSale() == null) {
            throw new ProductNotFoundException("product is incomplete", "verify content unit of measure or is on sale");
        }
        if(!this.validateExpirationDate(product.getExpirationDate())) {
            throw new ProductEntryDateException("expiration date invalid", "verify expiration date");
        }
        if(this.validateExistsCodeProduct(product.getCodProduct())){
            throw new ProductCodeException("code product already exist", "change code product");
        }
        Product prod = this.saveProductData(product, image);
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
    public Product updateProduct(Long idOriginalProduct,
                                 ProductDTO product,
                                 MultipartFile image) throws IOException {

        Product originalProduct = this.getProduct(idOriginalProduct);
        if(originalProduct == null) {
            throw new ProductNotFoundException("product not found", "product no exist in database");
        }
        if(!this.validateExpirationDate(product.getExpirationDate())) {
            throw new ProductEntryDateException("expiration date invalid", "verify expiration date");
        }
        if(this.validateExistsCodeProduct(product.getCodProduct())){
            throw new ProductCodeException("code product already exist", "change code product");
        }
        Product updateProduct = this.updateProductData(originalProduct, product, image);

        return this.productDAO.save(updateProduct);
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
            throw new ProductNotFoundException("product not found", "product no exist in database");
        }
        String productName = product.getName().toLowerCase();
        String inputName = name.toLowerCase();
        if(productName.isEmpty() || !productName.equals(inputName)) {
            throw new ProductNotFoundException("product not found", "product no exist in database " +
                                                "verify name product");
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
            throw new ProductNotFoundException("product not found", "product no exist in database");
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
    private boolean validateExistsCodeProduct(String code){
        List<Product> products = this.getAllProducts();
        for(Product product : products){
            if(product.getCodProduct().equals(code)){
                return true;
            }
        }
        return false;
    }
    private Product saveProductData(ProductDTO productDTO, MultipartFile image) throws IOException {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .expirationDate(productDTO.getExpirationDate())
                .entryDate(LocalDate.now())
                .stock(productDTO.getStock())
                .codProduct(productDTO.getCodProduct())
                .imageData(image.getBytes())
                .imageName(image.getOriginalFilename())
                .imageType(image.getContentType())
                .cost(productDTO.getCost())
                .isOnSale(productDTO.getIsOnSale())
                .unitOfMeasure(productDTO.getUnitOfMeasure())
                .build();
    }

    private Product updateProductData(Product originalProduct,
                                      ProductDTO productDTO,
                                      MultipartFile image) throws IOException {
        return originalProduct.toBuilder()
                .name(productDTO.getName() != null ? productDTO.getName() : originalProduct.getName())
                .codProduct(productDTO.getCodProduct() != null ? productDTO.getCodProduct() : originalProduct.getCodProduct())
                .description(productDTO.getDescription() != null ? productDTO.getDescription() : originalProduct.getDescription())
                .price(productDTO.getPrice() != null ? productDTO.getPrice() : originalProduct.getPrice())
                .cost(productDTO.getCost() != null ? productDTO.getCost() : originalProduct.getCost())
                .stock(productDTO.getStock() != null ? productDTO.getStock() : originalProduct.getStock())
                .entryDate(productDTO.getEntryDate() != null ? productDTO.getEntryDate() : originalProduct.getEntryDate())
                .expirationDate(productDTO.getExpirationDate() != null ? productDTO.getExpirationDate() : originalProduct.getExpirationDate())
                .isOnSale(productDTO.getIsOnSale() != null ? productDTO.getIsOnSale() : originalProduct.getIsOnSale())
                .unitOfMeasure(productDTO.getUnitOfMeasure() != null ? productDTO.getUnitOfMeasure() : originalProduct.getUnitOfMeasure())
                .imageData(image != null ? image.getBytes() : originalProduct.getImageData())
                .imageName(image != null ? image.getOriginalFilename() : originalProduct.getImageName())
                .imageType(image != null ? image.getContentType() : originalProduct.getImageType())
                .build();
    }


    private boolean validateExpirationDate(LocalDate expirationDate) {
        LocalDate today = LocalDate.now();
        return expirationDate.isAfter(today);
    }

}
